package at.dinauer.fhbay;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static at.dinauer.fhbay.FhBayMatchers.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.ArticleState;
import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.CategoryAdminRemote;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.test.categories.IntegrationTest;
import at.dinauer.fhbay.util.DateUtil;

@org.junit.experimental.categories.Category(IntegrationTest.class)
public class ArticleRoundTripTest {
	private ArticleAdminRemote articleAdmin;
	private CustomerAdminRemote customerAdmin;
	private CategoryAdminRemote categoryAdmin;
	
	@Before
	public void lookupBeans() throws Exception {
		ServiceLocator serviceLocator = new ServiceLocator();
		
		articleAdmin = serviceLocator.locate(ArticleAdminRemote.class);
		customerAdmin = serviceLocator.locate(CustomerAdminRemote.class);
		categoryAdmin = serviceLocator.locate(CategoryAdminRemote.class);
	}
	
	@Before
	public void clearDatabase() throws Exception {
		new DatabaseCleaner().clean();
	}
	
	@Test
	public void canSaveAndRetrieveAnArticle() throws Exception {
		Article article = anArticle();
		
		Long id = articleAdmin.offerArticle(article, aSeller().getId());
		article.setId(id);
		
		Article persistedArticle = articleAdmin.findArticleById(id);
		
		assertThat(persistedArticle.getId(), is(equalTo(article.getId())));
		assertThat(persistedArticle.getName(), is(equalTo(article.getName())));
		assertThat(persistedArticle.getInitialPrice(), is(equalTo(article.getInitialPrice())));
		assertThat(persistedArticle.getFinalPrice(), is(equalTo(article.getFinalPrice())));
		assertSameDateDownToSecond(persistedArticle.getStartDate(), article.getStartDate());
		assertSameDateDownToSecond(persistedArticle.getEndDate(), article.getEndDate());
		assertThat(persistedArticle.getState(), is(equalTo(article.getState())));
		assertThat(persistedArticle.getDescription(), is(equalTo(article.getDescription())));
	}
	
	@Test
	public void canSaveMultipleArticlesAndRetrieveAllOfThem() throws Exception {
		Article eos7D = articleWithName("Canon EOS 7D");
		Article eos1DX = articleWithName("Canon EOS 1D X");
		
		Customer aSeller = aSeller();
		articleAdmin.offerArticle(eos7D, aSeller.getId());
		articleAdmin.offerArticle(eos1DX, aSeller.getId());
		
		List<Article> articles = articleAdmin.findAllArticles();
		
		assertThat(articles, containsInAnyOrder(
				anArticleWithName("Canon EOS 7D"),
				anArticleWithName("Canon EOS 1D X")));
	}
	
	@Test
	public void findsOnlyArticlesMatchingAPatternInGivenCategory() throws Exception {
		Article eos7D = articleWithName("Canon EOS 7D");
		Article d40 = articleWithName("Nikon D40");
		Article nex7 = articleWithName("Sony Alpha NEX-7");

		
		Category cameras = categoryWithName("Cameras");
		nex7.addCategory(cameras);
		eos7D.addCategory(cameras);
		d40.addCategory(cameras);
		
		Article kd84 = articleWithName("Sony KD-84X9005");
		
		Category tvs = categoryWithName("TVs");
		kd84.addCategory(tvs);
		
		Customer aSeller = aSeller();
		
		eos7D.setSeller(aSeller);
		d40.setSeller(aSeller);
		nex7.setSeller(aSeller);
		kd84.setSeller(aSeller);
		
		Category photography = new Category();
		photography.setName("Photography");
		photography.setId(categoryAdmin.saveCategory(photography));
		cameras.setParent(photography);
		
		cameras.setId(categoryAdmin.saveCategory(cameras));
		tvs.setId(categoryAdmin.saveCategory(tvs));
		
		eos7D.setId(articleAdmin.offerArticle(eos7D, aSeller.getId()));
		d40.setId(articleAdmin.offerArticle(d40, aSeller.getId()));
		nex7.setId(articleAdmin.offerArticle(nex7, aSeller.getId()));
		kd84.setId(articleAdmin.offerArticle(kd84, aSeller.getId()));
		
		List<Article> articlesInCameras = articleAdmin.findAllMatchingArticles(cameras.getId(), "Sony", true);
		
		assertThat(articlesInCameras, contains(anArticleWithName("Sony Alpha NEX-7")));
		assertThat(articlesInCameras, not(contains(anArticleWithName("Canon EOS 7D"))));
		assertThat(articlesInCameras, not(contains(anArticleWithName("Nikon D40"))));
		assertThat(articlesInCameras, not(contains(anArticleWithName("Sony KD-84X9005"))));
		
		
		List<Article> articlesInPhotographyOrSubcategories = articleAdmin.findAllMatchingArticles(photography.getId(), "Sony", true);
		
		assertThat(articlesInPhotographyOrSubcategories, contains(anArticleWithName("Sony Alpha NEX-7")));
		assertThat(articlesInPhotographyOrSubcategories, not(contains(anArticleWithName("Canon EOS 7D"))));
		assertThat(articlesInPhotographyOrSubcategories, not(contains(anArticleWithName("Nikon D40"))));
		assertThat(articlesInPhotographyOrSubcategories, not(contains(anArticleWithName("Sony KD-84X9005"))));
	}
	
	@Test
	public void canAssignAnArticleToCategory() throws Exception {
		Article article = anArticle();
		Category category = categoryWithName("Cables");
		
		Long articleId = articleAdmin.offerArticle(article, aSeller().getId());
		Long categoryId = categoryAdmin.saveCategory(category);
		
		articleAdmin.assignArticleToCategory(articleId, categoryId);
		
		Article persistedArticle = articleAdmin.findArticleById(articleId);
		
		assertThat(persistedArticle.getCategories(), contains(aCategoryWithName("Cables")));
	}

	private Category categoryWithName(String name) {
		Category category = new Category();
		
		category.setName(name);
		
		return category;
	}

	private Article articleWithName(String name) {
		Article article = anArticle();
		
		article.setName(name);
		
		return article;
	}

	private Article anArticle() {
		Article article = new Article();
		
		article.setName("Canon EOS 500D");
		article.setInitialPrice(399.00);
		article.setFinalPrice(547.00);
		article.setStartDate(DateUtil.addSeconds(DateUtil.now(), -10));
		article.setEndDate(DateUtil.addSeconds(DateUtil.now(), 100));
		article.setDescription("DSLR Camera");
		article.setState(ArticleState.OFFERED);
		
		return article;
	}

	private Customer aSeller() {
		Customer seller = new Customer();
		
		seller.setUserName("tom.seller");
		seller.setFirstName("Tom");
		seller.setLastName("Seller");
		
		seller.setId(customerAdmin.saveCustomer(seller));
		return seller;
	}
	
	public void assertSameDateDownToSecond(Date expected, Date actual) {
		long actualSeconds = actual.getTime() / 1000;
		long expectedSeconds = expected.getTime() / 1000;
		assertThat(expectedSeconds, is(equalTo(actualSeconds)));
	}
}
