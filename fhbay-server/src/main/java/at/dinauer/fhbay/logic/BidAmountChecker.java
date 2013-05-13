package at.dinauer.fhbay.logic;

import java.util.List;

import at.dinauer.fhbay.domain.Bid;

public class BidAmountChecker {

	private BidSorter bidSorter;
	private double minimumIncrement;

	public BidAmountChecker(BidSorter bidSorter, double minimumIncrement) {
		this.bidSorter = bidSorter;
		this.minimumIncrement = minimumIncrement;
	}

	public boolean isAmountHighEnough(List<Bid> bids, double initialPrice, double amountToCheck) {
		if (amountToCheck < initialPrice) return false;
		if (bids.size() > 0) {
			List<Bid> sortedBids = bidSorter.sortBidsByAmountDescending(bids);
			
			return amountToCheck > (sortedBids.get(0).getAmount() + minimumIncrement);
		}
		
		return true;
	}

}
