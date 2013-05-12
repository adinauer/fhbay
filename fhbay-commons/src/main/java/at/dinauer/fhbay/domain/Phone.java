package at.dinauer.fhbay.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Phone implements Serializable {
	private static final long	serialVersionUID	= 1L;

  @Id @GeneratedValue
  private long id;
  
  private String countryCode;
  
  private String number;
	
  public Phone() {
	}
  
	public Phone(String countryCode, String number) {
		this.countryCode = countryCode;
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return countryCode + " " + number;
	}
}
