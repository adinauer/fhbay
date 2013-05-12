package at.dinauer.fhbay.interfaces;

import javax.ejb.Local;

import at.dinauer.fhbay.domain.Article;


@Local
public interface AuctionLocal extends Auction {
	void addAuctionFinishTimer(Article article);
}
