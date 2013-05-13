package at.dinauer.fhbay.logic;

import java.util.List;

import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.util.BidSorter;

public class BidAmountChecker {

	private BidSorter bidSorter;
	private double minimumIncrement;

	public BidAmountChecker(BidSorter bidSorter, double minimumIncrement) {
		this.bidSorter = bidSorter;
		this.minimumIncrement = minimumIncrement;
	}

	public boolean isAmountHighEnough(List<Bid> bids, double initialPrice, double amountToCheck) {
		if (amountToCheck < initialPrice) return false;
		List<Bid> sortedBids = bidSorter.sortBidsByAmountDescending(bids);
		if (bids.size() == 1) {
			return amountToCheck > (initialPrice + minimumIncrement);
		}
		if (bids.size() > 1) {
			return amountToCheck > (sortedBids.get(1).getAmount() + minimumIncrement);
		}
		
		return true;
	}

}
