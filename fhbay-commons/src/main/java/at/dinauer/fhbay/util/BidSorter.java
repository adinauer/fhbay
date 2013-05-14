package at.dinauer.fhbay.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import at.dinauer.fhbay.domain.Bid;

public class BidSorter {

	public List<Bid> sortBidsByAmountDescending(List<Bid> unsortedBids) {
		List<Bid> bids = copyBids(unsortedBids);
		
		Collections.sort(bids, new Comparator<Bid>() {
			public int compare(Bid b1, Bid b2) {
				int amountComparison = (-1) * new Double(b1.getAmount()).compareTo(b2.getAmount());
				int comparison = amountComparison;
				
				if (isSameAmount(amountComparison)) {
					comparison = b1.getBidTime().compareTo(b2.getBidTime());
				}
				
				return comparison;
			}

			private boolean isSameAmount(int amountComparison) {
				return amountComparison == 0;
			}
		});
		
		return bids;
	}

	private List<Bid> copyBids(List<Bid> bids) {
		return new ArrayList<>(bids);
	}
}
