package at.dinauer.fhbay.beans;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.interfaces.AuctionLocal;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.interfaces.dao.ArticleDao;


@Stateless
public class AuctionBean implements AuctionLocal, AuctionRemote {

	//public void placeBid(...)
	//public x getBidInfo(...)

	@Resource
	private TimerService timerService;
	
	@EJB
	private ArticleDao articleDao;
	
	public void addAuctionFinishTimer(Article article) {
		timerService.createTimer(article.getEndDate(), article.getId());
	}
	
	@Timeout
	public void finishAuction(Timer timer) {
		Long articleId = (Long) timer.getInfo();
		Article article = articleDao.findById(articleId);

		// TODO: end auction
		System.out.println("$$$$$ auction has ended for article " + article.getName());
	}
}
