package at.dinauer.fhbay.beans.dao;


import java.util.List;

import javax.ejb.Stateless;

import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.interfaces.dao.BidDao;


@Stateless
public class BidDaoBean extends AbstractDaoBean<Bid, Long> implements BidDao {

	public List<Bid> findBidsForArticle(Long articleId) {
//		return getEntityManager().createQuery("SELECT c FROM Category c WHERE c.parent IS NULL").getResultList();
		return null;
	}
}