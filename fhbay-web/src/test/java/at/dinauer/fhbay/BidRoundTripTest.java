package at.dinauer.fhbay;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static at.dinauer.fhbay.domain.BidInfo.*;
import static at.dinauer.fhbay.FhBayMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.ArticleState;
import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.test.categories.IntegrationTest;
import at.dinauer.fhbay.util.DateUtil;

@Category(IntegrationTest.class)
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
		article.setInitialPrice(50.0);
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
	
	@Test
	public void rejectsBidThatIsTooLow() throws Exception {
		Article article = anArticle();
		article.setInitialPrice(50.0);
		
		Long articleId = articleAdmin.offerArticle(article, aSeller().getId());
		
		BidInfo bidInfo = auction.placeBid(articleId, aBidder().getId(), 10.0);
		
		assertThat(bidInfo, is(TOO_LOW));
	}
	
	
	@Test
	public void findsBidsForArticle() throws Exception {
		Long eos7D = articleAdmin.offerArticle(anArticle(), aSeller().getId());
		Long nex5 = articleAdmin.offerArticle(anArticle(), aSeller().getId());
		
		auction.placeBid(eos7D, aBidder().getId(), 800.0);
		auction.placeBid(eos7D, aBidder().getId(), 999.0);
		auction.placeBid(nex5, aBidder().getId(), 50.0);
		
		List<Bid> bids = auction.findBidsForArticle(eos7D);
		
		assertThat(bids, containsInAnyOrder(
				aBidWithAmount(800.0),
				aBidWithAmount(999.0)));
		assertThat(bids, not(contains(aBidWithAmount(50.0))));
	}
	
	@Test
	public void findsCurrentPriceForArticle() throws Exception {
		Long eos7D = articleAdmin.offerArticle(anArticle(), aSeller().getId());
		
		auction.placeBid(eos7D, aBidder().getId(), 800.0);
		auction.placeBid(eos7D, aBidder().getId(), 999.0);
		auction.placeBid(eos7D, aBidder().getId(), 1199.0);
		
		double currentPrice = auction.findCurrentPriceForArticle(eos7D);
		
		assertThat(currentPrice, is(equalTo(999.0)));
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
