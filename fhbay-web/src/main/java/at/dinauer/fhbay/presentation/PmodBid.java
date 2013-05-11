package at.dinauer.fhbay.presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PmodBid {
	private SimpleDateFormat dateAndTimeFormat;
	
	private String bidderName;
	private double amount;
	private Date bidTime;
	private double priceAtBidTime;
	private boolean isWinning;
	
	public PmodBid() {
		dateAndTimeFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
	}
	
	public String getBidderName() {
		return bidderName;
	}
	
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Date getBidTime() {
		return bidTime;
	}
	
	public String getBidTimeFormatted() {
		return dateAndTimeFormat.format(bidTime);
	}
	
	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}
	
	public double getPriceAtBidTime() {
		return priceAtBidTime;
	}
	
	public void setPriceAtBidTime(double priceAtBidTime) {
		this.priceAtBidTime = priceAtBidTime;
	}

	public boolean isWinning() {
		return isWinning;
	}
	
	public void setWinning(boolean isWinning) {
		this.isWinning = isWinning;
	}
}
