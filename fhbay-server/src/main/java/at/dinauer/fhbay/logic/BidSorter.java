package at.dinauer.fhbay.logic;

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
				return (-1) * new Double(b1.getAmount()).compareTo(b2.getAmount());
			}
		});
		
		return bids;
	}

	private List<Bid> copyBids(List<Bid> bids) {
		return new ArrayList<>(bids);
	}
}
