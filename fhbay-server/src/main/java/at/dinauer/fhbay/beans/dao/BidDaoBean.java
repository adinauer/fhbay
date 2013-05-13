package at.dinauer.fhbay.beans.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.interfaces.dao.BidDao;


@Stateless
public class BidDaoBean extends AbstractDaoBean<Bid, Long> implements BidDao {

	public List<Bid> findBidsForArticle(Long articleId) {
		TypedQuery<Bid> bidsForArticleQuery = getEntityManager().createQuery("SELECT b FROM Bid b INNER JOIN b.article a WHERE a.id = :articleId", Bid.class);
		
		bidsForArticleQuery.setParameter("articleId", articleId);
		
		return bidsForArticleQuery.getResultList();
	}
}