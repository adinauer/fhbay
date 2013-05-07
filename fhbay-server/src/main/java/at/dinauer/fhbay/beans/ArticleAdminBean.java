package at.dinauer.fhbay.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.ArticleAdminLocal;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.AuctionLocal;
import at.dinauer.fhbay.interfaces.dao.ArticleDao;
import at.dinauer.fhbay.interfaces.dao.CustomerDao;


@Stateless
public class ArticleAdminBean implements ArticleAdminLocal, ArticleAdminRemote {
	
	@EJB
	private CustomerDao customerDao;
	
	@EJB
	private ArticleDao articleDao;
//	private CategoryDao categoryDao;
	
	@EJB
	private AuctionLocal auction;

	public Article findArticleById(Long id) throws IdNotFoundException {
		Article article = articleDao.findById(id);
		
		if (article == null) throw new IdNotFoundException(id, "Article");
		
		return article;
	}

	public List<Article> findAllMatchingArticles(Long categoryId,
			String pattern, boolean includeSubCategories)
			throws IdNotFoundException {
//		Category category = categoryDao.findById(categoryId);
		return new ArrayList<>(findAllMatchingArticles(/*category, */pattern, includeSubCategories));
	}

	private Set<Article> findAllMatchingArticles(/* Category category, */String pattern, boolean includeSubCategories) throws IdNotFoundException {
		Set<Article> matchingArticles = articleDao.findByCategoryAndPattern(/*category.getId()*/null, pattern); 
		
//		if (includeSubCategories) {
//			for (Category subCategory : category.getSubCategories()) {
//				matchingArticles.addAll(findAllMatchingArticles(pattern, includeSubCategories));
//			}
//		}
		
		return matchingArticles;
	}
	
	public Long offerArticle(Article article, Long sellerId)
			throws IdNotFoundException {
		Customer seller = customerDao.findById(sellerId);
		
		if (seller == null) throw new IdNotFoundException(sellerId, "Customer");
		
		article.setSeller(seller);
		articleDao.persist(article);
		
		auction.addAuctionFinishTimer(article);
		
		return article.getId();
	}

}