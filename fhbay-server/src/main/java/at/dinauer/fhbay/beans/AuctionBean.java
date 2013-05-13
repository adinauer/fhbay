package at.dinauer.fhbay.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.ArticleState;
import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.AuctionLocal;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.interfaces.dao.ArticleDao;
import at.dinauer.fhbay.interfaces.dao.BidDao;
import at.dinauer.fhbay.interfaces.dao.CustomerDao;
import at.dinauer.fhbay.logic.BidAmountChecker;
import at.dinauer.fhbay.logic.BidSorter;
import at.dinauer.fhbay.logic.CurrentPriceCalculator;


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
		timerService.createTimer(article.getEndDate(), article.getId());
	}
	
	@Timeout
	public void finishAuction(Timer timer) throws IdNotFoundException {
		Long articleId = (Long) timer.getInfo();
		Article article = articleDao.findById(articleId);

		if (article == null) {
			System.out.println("tried to end auction for article, but no article found with id: " + articleId);
		} else {
			 double currentPrice = findCurrentPriceForArticle(articleId);
			 article.setFinalPrice(currentPrice);
			 if (haveBidsBeenPlacedForArticle(articleId)) {
				 article.setState(ArticleState.SOLD);
			 } else {
				 article.setState(ArticleState.UNSALEABLE);
			 }
			System.out.println("$$$$$ auction has ended for article " + article.getName() + ", state is now: " + article.getState());
		}
	}

	private boolean haveBidsBeenPlacedForArticle(Long articleId)
			throws IdNotFoundException {
		return findBidsForArticle(articleId).size() > 0;
	}

	public BidInfo placeBid(Long articleId, Long customerId, double amount) throws IdNotFoundException {
		Article article = articleDao.findById(articleId);
		if (article == null) throw new IdNotFoundException(articleId, "article");
		
		Date now = new Date();
		if (isBidTooEarly(article, now)) return BidInfo.TOO_EARLY;
		if (isBidTooLate(article, now)) return BidInfo.TOO_LATE;
		if (isAmountTooLow(articleId, amount, article.getInitialPrice())) return BidInfo.TOO_LOW; 
		
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

	private boolean isAmountTooLow(Long articleId, double amount, double initialPrice) throws IdNotFoundException {
		// todo extract constant for minimum increment
		BidAmountChecker amountChecker = new BidAmountChecker(new BidSorter(), 1.0);
		List<Bid> bids = findBidsForArticle(articleId);
		
		return !amountChecker.isAmountHighEnough(bids, initialPrice, amount);
	}

	private boolean isBidTooLate(Article article, Date now) {
		return now.getTime() > article.getEndDate().getTime();
	}

	private boolean isBidTooEarly(Article article, Date now) {
		return now.getTime() < article.getStartDate().getTime();
	}

	public List<Bid> findBidsForArticle(Long articleId) throws IdNotFoundException {
		return bidDao.findBidsForArticle(articleId);
	}

	public double findCurrentPriceForArticle(Long articleId) throws IdNotFoundException {
		CurrentPriceCalculator priceCalculator = new CurrentPriceCalculator(new BidSorter());
		List<Bid> bids = findBidsForArticle(articleId);
		
		double initialPrice = articleDao.findById(articleId).getInitialPrice();
		
		return priceCalculator.calculateCurrentPrice(bids, initialPrice);
	}
}
