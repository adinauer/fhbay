package at.dinauer.fhbay.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.interfaces.CategoryAdminRemote;
import at.dinauer.fhbay.presentation.PmodArticle;
import at.dinauer.fhbay.presentation.PmodBid;
import at.dinauer.fhbay.presentation.PmodCategory;

public class DataFetcher {

	private ServiceLocator serviceLocator;
	private ArticleAdminRemote articleAdmin;
	private AuctionRemote auction;
	private CategoryAdminRemote categoryAdmin;
	
	public DataFetcher() throws Exception {
		serviceLocator = new ServiceLocator();
		
		articleAdmin = serviceLocator.locate(ArticleAdminRemote.class);
		auction = serviceLocator.locate(AuctionRemote.class);
		categoryAdmin = serviceLocator.locate(CategoryAdminRemote.class);
	}

	public void fetchCategoryBreadcrumbs(Model model, String categoryId) {
		if (categoryId != null) {
			try {
				Category category = categoryAdmin.findCategoryById(Long.parseLong(categoryId)); 
							
				model.addAttribute("selectedCategoryName", category.getBreadcrumbs());
			} catch (IdNotFoundException | NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void fetchBids(Model model, long articleId) throws Exception {
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

	public void fetchCategories(Model model) throws Exception {
		List<PmodCategory> categories = new ArrayList<>();
		
		for (Category rootCategory : categoryAdmin.findRootCategories()) {
			categories.add(new PmodCategory(rootCategory));
		}
		
		model.addAttribute("categories", categories);
	}

	public void fetchAllArticles(Model model) throws Exception {
		fetchArticles(model, null, "", true);
	}

	public void fetchArticlesByCategory(Model model, String categoryId) throws Exception {
		fetchArticles(model, Long.parseLong(categoryId), "", true);
	}

	public void fetchArticles(Model model, Long categoryId, String pattern, boolean includeSubCategories) throws Exception {
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

	public void fetchArticleById(Model model, String articleId) throws Exception {
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
}
