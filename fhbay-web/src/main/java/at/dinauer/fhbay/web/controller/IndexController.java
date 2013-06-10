package at.dinauer.fhbay.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.dinauer.fhbay.util.ServiceLocator;
import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.interfaces.CategoryAdminRemote;
import at.dinauer.fhbay.presentation.PmodArticle;
import at.dinauer.fhbay.presentation.PmodBid;
import at.dinauer.fhbay.presentation.PmodCategory;
import at.dinauer.fhbay.security.User;
import at.dinauer.fhbay.util.BidSorter;

@Controller
public class IndexController {

	private ServiceLocator serviceLocator;
	private ArticleAdminRemote articleAdmin;
	private AuctionRemote auction;
	private CategoryAdminRemote categoryAdmin;
	
	public IndexController() throws Exception {
		serviceLocator = new ServiceLocator();
		
		articleAdmin = serviceLocator.locate(ArticleAdminRemote.class);
		auction = serviceLocator.locate(AuctionRemote.class);
		categoryAdmin = serviceLocator.locate(CategoryAdminRemote.class);
	}
	
	@RequestMapping(value = {
			"/", 
			"/login"})
	public String showArticles(Model model) throws Exception {
		model.addAttribute("showArticleList", true);

		fetchCategories(model);
		fetchAllArticles(model);
		
		return "index";
	}
	
	@RequestMapping(value = "/loginfailed")
	public String loginFailed(Model model) throws Exception {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(Model model) throws Exception {
		return "redirect:/";
	}

	@RequestMapping(value = {
			"/article/{articleId}/{name}", 
			"/article/{articleId}"})
	public String showArticleDetails(Model model, @PathVariable("articleId") String articleId) throws Exception {
		model.addAttribute("showArticleDetails", true);

		fetchCategories(model);
		fetchArticleById(model, articleId);
		
		return "index";
	}

	@RequestMapping(value = {
			"/category/{categoryId}/{categoryName}/{subCategoryName}", 
			"/category/{categoryId}/{name}", 
			"/category/{categoryId}"})
	public String showArticlesInCategory(Model model, @PathVariable("categoryId") String categoryId) throws Exception {
		model.addAttribute("showArticleList", true);

		fetchCategories(model);
		fetchArticlesByCategory(model, categoryId);
		fetchCategoryBreadcrumbs(model, categoryId);

		return "index";
	}

	@RequestMapping(value = "/bidHistory")
	public String showBidHistory(Model model, @RequestParam("articleId") String articleId) throws Exception {
		model.addAttribute("showBids", true);

		fetchCategories(model);
		fetchBids(model, Long.parseLong(articleId));
		fetchArticleById(model, articleId);

		return "index";
	}
	
	@RequestMapping(value = "/bid", method = RequestMethod.POST)
	public String bidOnArticle(Model model, @RequestParam("articleId") Long articleId, @RequestParam("amount") String amount) throws Exception {
		System.out.println("received new bid for article " + articleId + ": " + amount);
		
		BidInfo bidInfo = auction.placeBid(articleId, getLoggedInUser().getId(), Double.parseDouble(amount));
		model.addAttribute("bidInfo", bidInfo);
		
		return "redirect:/article/" + articleId;
	}

	@RequestMapping(value = "/offerArticle", method = RequestMethod.GET)
	public String showOfferArticleForm(Model model) throws Exception {
		model.addAttribute("showOfferArticleForm", true);

		fetchCategories(model);
		
		return "index";
	}

	@RequestMapping(value = "/offerArticle", method = RequestMethod.POST)
	public String offerArticle(Model model, 
			@RequestParam("name") String name, 
			@RequestParam("initialPrice") String initialPrice,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("category") String categoryId,
			@RequestParam("description") String description) throws Exception {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

		Article article = new Article();
		
		article.setName(name);
		article.setInitialPrice(Double.parseDouble(initialPrice));
		article.setStartDate(dateFormat.parse(startDate));
		article.setEndDate(dateFormat.parse(endDate));
		article.setDescription(description);
		
		Long articleId = articleAdmin.offerArticle(article, getLoggedInUser().getId());
		
		Long categoryIdNumeric = Long.parseLong(categoryId);
		if (categoryIdNumeric > 0) {
			articleAdmin.assignArticleToCategory(articleId, categoryIdNumeric);
		}
		
		return "redirect:/article/" + articleId;
	}

	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam("q") String searchString, @RequestParam("category") String categoryId) throws Exception {
		model.addAttribute("showArticleList", true);
		model.addAttribute("searchString", searchString);

		fetchCategories(model);
		fetchArticles(model, Long.parseLong(categoryId), searchString, true);
		fetchCategoryBreadcrumbs(model, categoryId);
		
		return "index";
	}

	private void fetchCategoryBreadcrumbs(Model model, String categoryId) {
		if (categoryId != null) {
			try {
				Category category = categoryAdmin.findCategoryById(Long.parseLong(categoryId)); 
							
				model.addAttribute("selectedCategoryName", category.getBreadcrumbs());
			} catch (IdNotFoundException | NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void fetchBids(Model model, long articleId) throws Exception {
		try {
			List<PmodBid> bids = new ArrayList<>();
			
			List<Bid> bidsForArticle = auction.findBidsForArticle(articleId);
			bidsForArticle = new BidSorter().sortBidsByAmountDescending(bidsForArticle);
			
			PmodBid pmod = new PmodBid();
			boolean isFirstLoop = true;
			for (Bid bid : bidsForArticle) {
				// set amount as priceAtBidTime for the next bid(which is actually the previous bid in this loop; because of descending sort order) 
				pmod.setPriceAtBidTime(bid.getAmount());
				
				pmod = new PmodBid(bid);
				pmod.setWinning(isFirstLoop);
				
				bids.add(pmod);
				
				isFirstLoop = false;
			}
			
			// set priceAtBidTime to initialPrice for the first bid
			Article article = articleAdmin.findArticleById(articleId);
			pmod.setPriceAtBidTime(article.getInitialPrice());
			
			model.addAttribute("bids", bids);
		} catch (IdNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void fetchCategories(Model model) throws Exception {
		List<PmodCategory> categories = new ArrayList<>();
		
		for (Category rootCategory : categoryAdmin.findRootCategories()) {
			categories.add(new PmodCategory(rootCategory));
		}
		
		model.addAttribute("categories", categories);
	}

	private void fetchAllArticles(Model model) throws Exception {
		fetchArticles(model, null, "", true);
	}

	private void fetchArticlesByCategory(Model model, String categoryId) throws Exception {
		fetchArticles(model, Long.parseLong(categoryId), "", true);
	}

	private void fetchArticles(Model model, Long categoryId, String pattern, boolean includeSubCategories) throws Exception {
		List<PmodArticle> articles = new ArrayList<>();
		
		if (categoryId != null && categoryId <= 0) {
			for (Article article : articleAdmin.findAllMatchingArticles(pattern)) {
				articles.add(fetchArticleDetails(article));
			}
		} else if (categoryId != null) {
			try {
				for (Article article : articleAdmin.findAllMatchingArticles(categoryId, pattern, includeSubCategories)) {
					articles.add(fetchArticleDetails(article));				
				}	
			} catch (IdNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			for (Article article : articleAdmin.findAllArticles()) {
				articles.add(fetchArticleDetails(article));
			}
		}
		
		model.addAttribute("articles", articles);
	}

	private void fetchArticleById(Model model, String articleId) throws Exception {
		try {
			Article article = articleAdmin.findArticleById(Long.parseLong(articleId));
			PmodArticle selectedArticle = fetchArticleDetails(article);
			
			model.addAttribute("selectedArticle", selectedArticle);	
		} catch (IdNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private PmodArticle fetchArticleDetails(Article article) throws Exception {
		PmodArticle pmodArticle = new PmodArticle(article);

		pmodArticle.setCurrentPrice(auction.findCurrentPriceForArticle(article.getId()));
		pmodArticle.setNumberOfBids(auction.findBidsForArticle(article.getId()).size());

		Customer seller = article.getSeller();
		pmodArticle.setSellerName(String.format("%s %s (%s)", seller.getFirstName(), seller.getLastName(), seller.getUserName()));
		
		return pmodArticle;
	}

	private User getLoggedInUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
