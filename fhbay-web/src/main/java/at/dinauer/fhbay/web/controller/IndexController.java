package at.dinauer.fhbay.web.controller;

import java.text.SimpleDateFormat;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.security.User;
import at.dinauer.fhbay.util.DataFetcher;
import at.dinauer.fhbay.util.ServiceLocator;

@Controller
public class IndexController {

	private DataFetcher dataFetcher;
	
	private ServiceLocator serviceLocator;
	private ArticleAdminRemote articleAdmin;
	private AuctionRemote auction;
	
	public IndexController() throws Exception {
		dataFetcher = new DataFetcher();
		
		serviceLocator = new ServiceLocator();
		
		articleAdmin = serviceLocator.locate(ArticleAdminRemote.class);
		auction = serviceLocator.locate(AuctionRemote.class);
	}
	
	@RequestMapping(value = {
			"/", 
			"/login"})
	public String showArticles(Model model) throws Exception {
		model.addAttribute("showArticleList", true);

		dataFetcher.fetchCategories(model);
		dataFetcher.fetchAllArticles(model);
		
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

		dataFetcher.fetchCategories(model);
		dataFetcher.fetchArticleById(model, articleId);
		
		return "index";
	}

	@RequestMapping(value = {
			"/category/{categoryId}/{categoryName}/{subCategoryName}", 
			"/category/{categoryId}/{name}", 
			"/category/{categoryId}"})
	public String showArticlesInCategory(Model model, @PathVariable("categoryId") String categoryId) throws Exception {
		model.addAttribute("showArticleList", true);

		dataFetcher.fetchCategories(model);
		dataFetcher.fetchArticlesByCategory(model, categoryId);
		dataFetcher.fetchCategoryBreadcrumbs(model, categoryId);

		return "index";
	}

	@RequestMapping(value = "/bidHistory")
	public String showBidHistory(Model model, @RequestParam("articleId") String articleId) throws Exception {
		model.addAttribute("showBids", true);

		dataFetcher.fetchCategories(model);
		dataFetcher.fetchBids(model, Long.parseLong(articleId));
		dataFetcher.fetchArticleById(model, articleId);

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

		dataFetcher.fetchCategories(model);
		
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

		dataFetcher.fetchCategories(model);
		dataFetcher.fetchArticles(model, Long.parseLong(categoryId), searchString, true);
		dataFetcher.fetchCategoryBreadcrumbs(model, categoryId);
		
		return "index";
	}

	private User getLoggedInUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
