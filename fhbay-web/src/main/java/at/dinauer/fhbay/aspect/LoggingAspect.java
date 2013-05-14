package at.dinauer.fhbay.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.showArticles(..))")
	public void logArticles(JoinPoint joinPoint) {
		System.out.println("showing all articles");
	}

	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.showArticleDetails(..))")
	public void logArticleDetails(JoinPoint joinPoint) {
		System.out.println("showing article details for article with id: " + joinPoint.getArgs()[1]);
	}

	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.showArticlesInCategory(..))")
	public void logArticlesInCategory(JoinPoint joinPoint) {
		System.out.println("showing articles in category with id: " + joinPoint.getArgs()[1]);
	}

	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.showBidHistory(..))")
	public void logBidHistory(JoinPoint joinPoint) {
		System.out.println("showing bid history for article with id: " + joinPoint.getArgs()[1]);
	}

	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.bidOnArticle(..))")
	public void logBid(JoinPoint joinPoint) {
		System.out.println("placing a bid for article with id: " + joinPoint.getArgs()[1] + " with amount: " + joinPoint.getArgs()[2]);
	}

	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.offerArticle(..))")
	public void logOfferArticle(JoinPoint joinPoint) {
		System.out.println(String.format("offering article: name=%s; initialPrice=%s; startDate=%s; endDate=%s; category=%s; description=%s",
				joinPoint.getArgs()[1],
				joinPoint.getArgs()[2],
				joinPoint.getArgs()[3],
				joinPoint.getArgs()[4],
				joinPoint.getArgs()[5],
				joinPoint.getArgs()[6]
			));
	}

	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.search(..))")
	public void logSearch(JoinPoint joinPoint) {
		System.out.println("searching for: " + joinPoint.getArgs()[1] + " in category: " + joinPoint.getArgs()[2]);
	}
	
	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.logout(..))")
	public void logLogout(JoinPoint joinPoint) {
		System.out.println("logging out");
	}
	
	@Before("execution(* at.dinauer.fhbay.web.controller.IndexController.loginFailed(..))")
	public void logLoginFailed(JoinPoint joinPoint) {
		System.out.println("login failed");
	}
}
