package at.dinauer.fhbay.logic;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.util.BidSorter;



public class CurrentPriceCalculatorTest {
	private static final List<Bid> NO_BIDS = Collections.<Bid> emptyList();
	private CurrentPriceCalculator calculator;
	
	@Before
	public void createCalculator() {
		calculator = new CurrentPriceCalculator(new BidSorter());
	}
	
	@Test
	public void returnsInitialPriceIfNoBidsHaveBeenPlaced() {
		double currentPrice = calculator.calculateCurrentPrice(NO_BIDS, 9.99);
		
		assertThat(currentPrice, is(equalTo(9.99)));
	}
	
	@Test
	public void returnsInitialPriceIfOnlyOneBidHasBeenPlaced() {
		double currentPrice = calculator.calculateCurrentPrice(bids(15.0), 9.99);
		
		assertThat(currentPrice, is(equalTo(9.99)));
		
	}
	
	@Test
	public void returnsSecondHighestBidIfMultipleBidsHaveBeenPlaced() {
		double currentPrice = calculator.calculateCurrentPrice(bids(11.0, 15.0, 20.0, 25.0), 9.99);
		
		assertThat(currentPrice, is(equalTo(20.0)));
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
