package at.dinauer.fhbay.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.util.BidSorter;



public class BidAmountCheckerTest {
	private static final List<Bid> NO_BIDS = Collections.<Bid> emptyList();
	private static final double MINIMUM_INCREMENT = 1.0;
	private BidAmountChecker checker;
	
	@Before
	public void createCalculator() {
		checker = new BidAmountChecker(new BidSorter(), MINIMUM_INCREMENT);
	}
	

	@Test
	public void rejectsABidThatIsLowerThanTheInitialPrice() {
		boolean isHighEnough = checker.isAmountHighEnough(NO_BIDS, 100.0, 50.0);
		
		assertThat(isHighEnough, is(false));
	}
	
	@Test
	public void acceptsABidThatIsHigherThanTheInitialPrice() {
		boolean isHighEnough = checker.isAmountHighEnough(NO_BIDS, 100.0, 200.0);
		
		assertThat(isHighEnough, is(true));
	}

	@Test
	public void acceptsABidThatIsHigherThanAllPreviousBids() {
		boolean isHighEnough = checker.isAmountHighEnough(bids(11.0, 15.0, 20.0, 25.0), 10.0, 100.0);
		
		assertThat(isHighEnough, is(true));
	}

	@Test
	public void acceptsABidThatIsHigherThanTheSecondHighestBid() {
		boolean isHighEnough = checker.isAmountHighEnough(bids(11.0, 15.0, 20.0, 25.0), 10.0, 22.0);
		
		assertThat(isHighEnough, is(true));
	}

	@Test
	public void rejectsABidThatIsLowerThanTheSecondHighestBid() {
		boolean isHighEnough = checker.isAmountHighEnough(bids(11.0, 15.0, 20.0, 25.0), 10.0, 19.0);
		
		assertThat(isHighEnough, is(false));
	}

	@Test
	public void rejectsABidThatIsAsHighAsTheSecondHighestBid() {
		boolean isHighEnough = checker.isAmountHighEnough(bids(11.0, 15.0, 20.0, 25.0), 10.0, 20.0);
		
		assertThat(isHighEnough, is(false));
	}
	
	@Test
	public void rejectsABidThatIsNotHigherThanTheSecondHighestBidPlusMinimumIncrement() {
		double secondHighestBid = 25.0;
		double lowerThanHighestBidPlusIncrement = secondHighestBid + MINIMUM_INCREMENT - 0.1;
		boolean isHighEnough = checker.isAmountHighEnough(bids(secondHighestBid, 30), 10.0, lowerThanHighestBidPlusIncrement);
		
		assertThat(isHighEnough, is(false));
	}
	
	@Test
	public void acceptsABidThatIsHigherThanTheInitialPricePlusMinimumIncrementButLowerThanTheHighestBid() {
		boolean isHighEnough = checker.isAmountHighEnough(bids(15.0), 10.0, 14.0);
		
		assertThat(isHighEnough, is(true));
	}
	
	@Test
	public void rejectsABidThatIsLowerThanInitialPricePlusMinimumIncrementIfOneBidHasBeenPlaced() {
		double initialPrice = 10.0;
		double lowerThanInitialPricePlusIncrement = initialPrice + MINIMUM_INCREMENT - 0.1;
		boolean isHighEnough = checker.isAmountHighEnough(bids(15.0), initialPrice, lowerThanInitialPricePlusIncrement);
		
		assertThat(isHighEnough, is(false));
	}
	
	@Test
	public void doesNotCareAboutMinimumIncrementIfNoBidsHaveBeenPlaced() {
		double initialPrice = 10.0;
		double lowerThanInitialPricePlusIncrement = initialPrice + MINIMUM_INCREMENT - 0.1;
		boolean isHighEnough = checker.isAmountHighEnough(NO_BIDS, initialPrice, lowerThanInitialPricePlusIncrement);
		
		assertThat(isHighEnough, is(true));
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
}
