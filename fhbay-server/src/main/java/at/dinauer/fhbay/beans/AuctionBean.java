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
		System.out.println("creating timer for article with id: " + article.getId());
		System.out.println("creating timer for article : " + article);
		timerService.createTimer(article.getEndDate(), article.getId());
	}
	
	@Timeout
	public void finishAuction(Timer timer) {
		Long articleId = (Long) timer.getInfo();
		if (articleId == null) {
			System.out.println("tried to end auction but article id was null");
			return;
		}
		Article article = articleDao.findById(articleId);

		if (article == null) {
			System.out.println("no article found with id: " + articleId);
		} else {
			// TODO: end auction
			System.out.println("$$$$$ auction has ended for article " + article.getName());
		}
	}
}
