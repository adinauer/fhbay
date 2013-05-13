package at.dinauer.fhbay.presentation;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.util.DateUtil;



public class PmodBidTest {
	@Test
	public void constructingAPresentationModelFromABidResultsInTheSameValues() {
		Bid bid = new Bid();
		
		bid.setAmount(123.45);
		bid.setBidTime(DateUtil.now());
		bid.setBidder(aBidder());
		
		String expectedBidderName = String.format("%s %s (%s)", bid.getBidder().getFirstName(), bid.getBidder().getLastName(), bid.getBidder().getUserName());
		
		PmodBid pmod = new PmodBid(bid);
		
		assertThat(pmod.getAmount(), is(equalTo(bid.getAmount())));
		assertThat(pmod.getBidderName(), is(equalTo(expectedBidderName)));
		assertThat(pmod.getBidTime(), is(equalTo(bid.getBidTime())));
	}

	private Customer aBidder() {
		Customer bidder = new Customer();
		bidder.setFirstName("Bud");
		bidder.setLastName("Bidder");
		bidder.setUserName("bud.bidder");
		return bidder;
	}
}
