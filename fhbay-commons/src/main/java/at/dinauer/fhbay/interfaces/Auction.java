package at.dinauer.fhbay.interfaces;

import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.exceptions.IdNotFoundException;

public interface Auction {
	BidInfo placeBid (Long articleid, Long customerId, double amount) throws IdNotFoundException;
	// getBidInfo = max bid
}