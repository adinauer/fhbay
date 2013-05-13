package at.dinauer.fhbay.util;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import at.dinauer.fhbay.domain.Bid;



public class BidSorterTest {
	private BidSorter sorter;
	
	@Before
	public void createSorter() {
		sorter = new BidSorter();
	}
	
	@Test
	public void sortsBids() {
		List<Bid> sortedBids = sorter.sortBidsByAmountDescending(bids(10.0, 5.0, 20.0, 15.0));
		
		assertThat(sortedBids, contains(
					aBidWithAmount(20.0),
					aBidWithAmount(15.0),
					aBidWithAmount(10.0),
					aBidWithAmount(5.0)
				));
	}
	
	@Test
	public void canHandleAnEmptyList() {
		List<Bid> sortedBids = sorter.sortBidsByAmountDescending(bids());
		
		assertThat(sortedBids, is(empty()));
	}

	private List<Bid> bids(double... amounts) {
		List<Bid> bids = new ArrayList<>();
		
		for (double amount : amounts) {
			bids.add(bidWithAmount(amount));
		}
		
		return bids;
	}

	private Bid bidWithAmount(double amount) {
		Bid bid = new Bid();
		
		bid.setAmount(amount);
		
		return bid;
	}
	
	public static Matcher<Bid> aBidWithAmount(double amount) {
		return new FeatureMatcher<Bid, Double>(equalTo(amount), "a bid with amount ", "was") {
			protected Double featureValueOf(Bid actual) {
				return actual.getAmount();
			}
		};
	}
}
