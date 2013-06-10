package at.dinauer.fhbay.presentation;

import org.joda.time.DateTime;
import org.junit.Test;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.util.DateUtil;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class PmodArticleTest {
	
	@Test
	public void constructingAPresentationModelFromAnArticleShouldResultInTheSameValues() {
		Article article = new Article();
		
		article.setId(123L);
		article.setName("Canon EOS 500D");
		article.setDescription("lorem ipsum");
		article.setInitialPrice(99.99);
		article.setStartDate(new DateTime().withYear(2000).toDate());
		article.setEndDate(new DateTime().withYear(2099).toDate());
		
		PmodArticle pmod = new PmodArticle(article);
		
		assertThat(pmod.getId(), is(equalTo(article.getId())));
		assertThat(pmod.getName(), is(equalTo(article.getName())));
		assertThat(pmod.getDescription(), is(equalTo(article.getDescription())));
		assertThat(pmod.getInitialPrice(), is(equalTo(article.getInitialPrice())));
		assertThat(pmod.getStartDate(), is(equalTo(article.getStartDate())));
		assertThat(pmod.getEndDate(), is(equalTo(article.getEndDate())));
	}
	
	@Test
	public void determinesThatAnAuctionHasStartedWhenItsStartDateIsEarlierThanTheCurrentTime() {
		PmodArticle pmod = new PmodArticle();
		pmod.setStartDate(DateUtil.addSeconds(DateUtil.now(), 60));
		
		assertThat(pmod.getHasAuctionStarted(), is(true));
	}
	
	@Test
	public void determinesThatAnAuctionHasNotStartedWhenItsStartDateIsLaterThanTheCurrentTime() {
		PmodArticle pmod = new PmodArticle();
		pmod.setStartDate(DateUtil.addSeconds(DateUtil.now(), -60));
		
		assertThat(pmod.getHasAuctionStarted(), is(not(true)));
	}
	
	@Test
	public void determinesThatAnAuctionHasEndedWhenItsEndDateIsEarlierThanTheCurrentTime() {
		PmodArticle pmod = new PmodArticle();
		pmod.setEndDate(DateUtil.addSeconds(DateUtil.now(), -60));
		
		assertThat(pmod.getHasAuctionEnded(), is(true));
	}
	
	@Test
	public void determinesThatAnAuctionHasNotEndedWhenItsEndDateIsLaterThanTheCurrentTime() {
		PmodArticle pmod = new PmodArticle();
		pmod.setEndDate(DateUtil.addSeconds(DateUtil.now(), 60));
		
		assertThat(pmod.getHasAuctionEnded(), is(not(true)));
	}
	
	@Test
	public void replacesBlanksInNameForUrlName() {
		PmodArticle pmod = new PmodArticle();
		pmod.setName("no more spaces");
		
		assertThat(pmod.getUrlName(), is(equalTo("no-more-spaces")));
	}
}
