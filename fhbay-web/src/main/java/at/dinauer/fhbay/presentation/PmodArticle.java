package at.dinauer.fhbay.presentation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.util.DateUtil;

public class PmodArticle {

	private static final int SECONDS_PER_DAY = 60 * 60 * 24;
	
	private Long id;
	private String name;
	private String description;
	private double initialPrice;
	private Date startDate;
	private Date endDate;
	private Date now;
	private PeriodFormatter timeRemainingFormat;
	private SimpleDateFormat deliveryDateFormat;
	private SimpleDateFormat dateAndTimeFormat;
	

	public PmodArticle() {
		now = DateUtil.now();
		
		deliveryDateFormat = new SimpleDateFormat("EEE. MMM. d", Locale.US);
		
		dateAndTimeFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
		
		timeRemainingFormat = new PeriodFormatterBuilder()
	        .appendDays().appendSuffix(" day ", " days ")
	        .appendHours().appendSuffix(" hour ", " hours ")
	        .appendMinutes().appendSuffix(" minute ", " minutes ")
	        .appendSeconds().appendSuffix(" second ", " seconds ")
	        .toFormatter();
	}
	
	public PmodArticle(Article article) {
		this();
		
		id = article.getId();
		name = article.getName();
		description = article.getDescription();
		initialPrice = article.getInitialPrice();
		startDate = article.getStartDate();
		endDate = article.getEndDate();
	}
	
	public int getNumberOfBids() {
		return 10;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public double getCurrentPrice() {
		return initialPrice;
	}
	
	public double getMinimumBid() {
		return initialPrice + 1.0;
	}
	
	public boolean getHasAuctionStarted() {
		return now.getTime() < startDate.getTime();
	}
	
	public boolean getHasAuctionEnded() {
		return DateUtil.now().getTime() > endDate.getTime();
	}
	
	public String getTimeRemainingFormatted() {
		Period timeRemaining = new Period(new DateTime(now), new DateTime(endDate));
		return timeRemainingFormat.print(timeRemaining);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrlName() {
		return name.replace(' ' , '-');
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double price) {
		this.initialPrice = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getEndDateFormatted() {
		return dateAndTimeFormat.format(endDate);
	}
	
	public String getDeliveryDateMinFormatted() {
		return deliveryDateFormat.format(DateUtil.addSeconds(endDate, 3 * SECONDS_PER_DAY));
	}
	
	public String getDeliveryDateMaxFormatted() {
		return deliveryDateFormat.format(DateUtil.addSeconds(endDate, 17 * SECONDS_PER_DAY));
	}
}
