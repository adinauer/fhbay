package at.dinauer.fhbay.interfaces;

import java.util.List;

import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.exceptions.IdNotFoundException;

public interface Auction {
	
	BidInfo placeBid (Long articleid, Long customerId, double amount) throws IdNotFoundException;
	
	List<Bid> findBidsForArticle(Long articleId) throws IdNotFoundException;

	double findCurrentPriceForArticle(Long articleId) throws IdNotFoundException;
}