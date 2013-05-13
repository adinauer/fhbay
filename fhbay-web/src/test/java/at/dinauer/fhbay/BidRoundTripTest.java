package at.dinauer.fhbay;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static at.dinauer.fhbay.domain.BidInfo.*;

import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.ArticleState;
import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.util.DateUtil;


public class BidRoundTripTest {
	private ArticleAdminRemote articleAdmin;
	private CustomerAdminRemote customerAdmin;
	private AuctionRemote auction;
	
	@Before
	public void lookupBeans() throws Exception {
		ServiceLocator serviceLocator = new ServiceLocator();
		
		auction = serviceLocator.locate(AuctionRemote.class);
		articleAdmin = serviceLocator.locate(ArticleAdminRemote.class);
		customerAdmin = serviceLocator.locate(CustomerAdminRemote.class);
	}
	
	@Before
	public void clearDatabase() throws Exception {
		new DatabaseCleaner().clean();
	}
	
	@Test
	public void acceptsBidThatIsInTime() throws Exception {
		Article article = anArticle();
		article.setStartDate(DateUtil.addSeconds(DateUtil.now(), -10));
		article.setEndDate(DateUtil.addSeconds(DateUtil.now(), 10));
		
		Long articleId = articleAdmin.offerArticle(article, aSeller().getId());
		
		BidInfo bidInfo = auction.placeBid(articleId, aBidder().getId(), 99.00);
		
		assertThat(bidInfo, is(ACCEPTED));
	}
	
	@Test
	public void rejectsBidThatIsTooEarly() throws Exception {
		Article article = anArticle();
		article.setStartDate(DateUtil.addSeconds(DateUtil.now(), 10));
		
		Long articleId = articleAdmin.offerArticle(article, aSeller().getId());
		
		BidInfo bidInfo = auction.placeBid(articleId, aBidder().getId(), 99.00);
		
		assertThat(bidInfo, is(TOO_EARLY));
	}
	
	@Test
	public void rejectsBidThatIsTooLate() throws Exception {
		Article article = anArticle();
		article.setEndDate(DateUtil.addSeconds(DateUtil.now(), -10));
		
		Long articleId = articleAdmin.offerArticle(article, aSeller().getId());
		
		BidInfo bidInfo = auction.placeBid(articleId, aBidder().getId(), 99.00);
		
		assertThat(bidInfo, is(TOO_LATE));
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

	private Customer aBidder() {
		Customer bidder = new Customer();
		
		bidder.setUserName("bud.bidder");
		bidder.setFirstName("Bud");
		bidder.setLastName("Bidder");
		
		bidder.setId(customerAdmin.saveCustomer(bidder));
		return bidder;
	}

	private Customer aSeller() {
		Customer seller = new Customer();
		
		seller.setUserName("sam.seller");
		seller.setFirstName("Sam");
		seller.setLastName("Seller");
		
		seller.setId(customerAdmin.saveCustomer(seller));
		return seller;
	}
}
