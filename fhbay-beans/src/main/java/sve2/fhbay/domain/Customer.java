package sve2.fhbay.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	@Column(length=20, nullable=true)
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
//	@Embedded wuerde die properties von Address als spalten in der Customer tabelle hinzufuegen
//	 ohne annotation wird die adresse serialisiert in einer spalte in der Customer tabelle gespeichert
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private Address billingAddress;
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	@OrderColumn(name="ordercol")
	private List<Address> shippingAddresses = new ArrayList<>();
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="phone")
	private Set<Phone> phoneNumbers = new HashSet<>();
	
	public Customer() {
		/* do nothing */
	}

	public Customer(String firstName, String lastName, String userName,
			String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public List<Address> getShippingAddresses() {
		return shippingAddresses;
	}
	
	public void addShippingAddress(Address shippingAddress) {
		shippingAddresses.add(shippingAddress);
	}
	
	public Set<Phone> getPhones() {
		return phoneNumbers;
	}
	public void addPhone(Phone phoneNumber) {
		phoneNumbers.add(phoneNumber);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%s %s (%s) %s", lastName, firstName, userName,	email));
		if (billingAddress != null) sb.append("; billing address: " + billingAddress);
		
		return sb.toString();
	}
}
