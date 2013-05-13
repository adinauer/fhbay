package at.dinauer.fhbay.beans;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.AuctionLocal;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.interfaces.dao.ArticleDao;
import at.dinauer.fhbay.interfaces.dao.BidDao;
import at.dinauer.fhbay.interfaces.dao.CustomerDao;


@Stateless
public class AuctionBean implements AuctionLocal, AuctionRemote {

	//public x getBidInfo(...)

	@Resource
	private TimerService timerService;
	
	@EJB
	private ArticleDao articleDao;
	
	@EJB
	private CustomerDao customerDao;
	
	@EJB
	private BidDao bidDao;
	
	public void addAuctionFinishTimer(Article article) {
		System.out.println("creating timer for article : " + article);
		timerService.createTimer(article.getEndDate(), article.getId());
	}
	
	@Timeout
	public void finishAuction(Timer timer) {
		Long articleId = (Long) timer.getInfo();
		Article article = articleDao.findById(articleId);

		if (article == null) {
			System.out.println("no article found with id: " + articleId);
		} else {
			// TODO: end auction
			System.out.println("$$$$$ auction has ended for article " + article.getName());
		}
	}

	public BidInfo placeBid(Long articleId, Long customerId, double amount) throws IdNotFoundException {
		Article article = articleDao.findById(articleId);
		if (article == null) throw new IdNotFoundException(articleId, "article");
		
		Date now = new Date();
		if (isBidTooEarly(article, now)) return BidInfo.TOO_EARLY;
		if (isBidTooLate(article, now)) return BidInfo.TOO_LATE;
		
		//TODO: isTooLow?
//		return BidInfo.TOO_LOW;
		
		Customer bidder = customerDao.findById(customerId);
		if (bidder == null) throw new IdNotFoundException(customerId, "customer");
		
		Bid bid = new Bid();
		bid.setAmount(amount);
		bid.setArticle(article);
		bid.setBidder(bidder);
		bid.setBidTime(now);
		
		bidDao.merge(bid);
		
		return BidInfo.ACCEPTED;
	}

	private boolean isBidTooLate(Article article, Date now) {
		return now.getTime() > article.getEndDate().getTime();
	}

	private boolean isBidTooEarly(Article article, Date now) {
		return now.getTime() < article.getStartDate().getTime();
	}
}
