package at.dinauer.fhbay.logic;

import java.util.List;

import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.util.BidSorter;

/**
 * Calculates the price according to the Vickrey Auction rules.
 *
 *  @see http://en.wikipedia.org/wiki/Vickrey_auction
 */
public class CurrentPriceCalculator {

	private BidSorter bidSorter;

	public CurrentPriceCalculator(BidSorter bidSorter) {
		this.bidSorter = bidSorter;
	}
	
	public double calculateCurrentPrice(List<Bid> bids, double initialPrice) {
		if (bids.size() <= 1) return initialPrice;
		
		List<Bid> sortedBids = bidSorter.sortBidsByAmountDescending(bids);
		
		return sortedBids.get(1).getAmount();
	}
}
