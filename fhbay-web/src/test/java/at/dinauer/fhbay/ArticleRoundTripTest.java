package at.dinauer.fhbay;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;



import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.ArticleState;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.util.DateUtil;


public class ArticleRoundTripTest {
	private ArticleAdminRemote articleAdmin;
	private CustomerAdminRemote customerAdmin;
	
	@Before
	public void lookupBeans() throws Exception {
		ServiceLocator serviceLocator = new ServiceLocator();
		articleAdmin = serviceLocator.locate(ArticleAdminRemote.class);
		customerAdmin = serviceLocator.locate(CustomerAdminRemote.class);
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
		Article eos7D = anArticle();
		eos7D.setName("Canon EOS 7D");
		
		Article eos1DX = anArticle();
		eos1DX.setName("Canon EOS 1D X");
		
		Customer aSeller = aSeller();
		articleAdmin.offerArticle(eos7D, aSeller.getId());
		articleAdmin.offerArticle(eos1DX, aSeller.getId());
		
		List<Article> articles = articleAdmin.findAllArticles();
		
		assertThat(articles, containsInAnyOrder(
				anArticleWithName("Canon EOS 7D"),
				anArticleWithName("Canon EOS 1D X")));
	}
	
	@Test
	public void findsOnlyArticlesMatchingAPattern() throws Exception {
		Article eos7D = anArticle();
		eos7D.setName("Canon EOS 7D");
		
		Article d40 = anArticle();
		d40.setName("Nikon D40");
		
		Article nex7 = anArticle();
		nex7.setName("Sony Alpha NEX-7");
		
		Customer aSeller = aSeller();
		articleAdmin.offerArticle(eos7D, aSeller.getId());
		articleAdmin.offerArticle(d40, aSeller.getId());
		articleAdmin.offerArticle(nex7, aSeller.getId());
		
		List<Article> articles = articleAdmin.findAllMatchingArticles(null, "Sony", true);
		
		assertThat(articles, contains(anArticleWithName("Sony Alpha NEX-7")));
		assertThat(articles, not(contains(anArticleWithName("Canon EOS 7D"))));
		assertThat(articles, not(contains(anArticleWithName("Nikon D40"))));
	}

	private Matcher<Article> anArticleWithName(final String name) {
		return new FeatureMatcher<Article, String>(equalTo(name), "article with name ", "was") {
			protected String featureValueOf(Article actual) {
				return actual.getName();
			}
		};
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
		seller.setId(customerAdmin.saveCustomer(seller));
		return seller;
	}
	
	public void assertSameDateDownToSecond(Date expected, Date actual) {
		long actualSeconds = actual.getTime() / 1000;
		long expectedSeconds = expected.getTime() / 1000;
		assertThat(expectedSeconds, is(equalTo(actualSeconds)));
	}
}
