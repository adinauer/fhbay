package sve2.fhbay.interfaces;

import javax.ejb.Local;

import sve2.fhbay.domain.Article;

@Local
public interface AuctionLocal extends Auction {
	// timer fuehrt das finish einer auktion durch
	void addAuctionFinishTimer(Article article);
}
