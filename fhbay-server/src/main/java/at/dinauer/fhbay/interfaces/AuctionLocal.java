package at.dinauer.fhbay.interfaces;

import javax.ejb.Local;

import at.dinauer.fhbay.domain.Article;


@Local
public interface AuctionLocal extends Auction {
	// timer fuehrt das finish einer auktion durch
	void addAuctionFinishTimer(Article article);
}
