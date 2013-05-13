package at.dinauer.fhbay.interfaces.dao;


import java.util.List;

import javax.ejb.Local;

import at.dinauer.fhbay.domain.Bid;



@Local
public interface BidDao extends Dao<Bid, Long> {
	List<Bid> findBidsForArticle(Long articleId);
}
