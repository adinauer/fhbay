package at.dinauer.fhbay.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import at.dinauer.fhbay.security.FhBayRoles;

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
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private Address billingAddress;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private Address shippingAddress;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@MapKeyColumn(name="phone_type")
	private Map<String, Phone> phoneNumbers = new HashMap<>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	@OrderColumn(name="ordercol")
	private List<PaymentData> paymentData = new ArrayList<>();

	@ElementCollection(targetClass = FhBayRoles.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<FhBayRoles> roles = new HashSet<>();
	
	
	public Customer() {}

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
	
	public Address getShippingAddress() {
		return shippingAddress;
	}
	
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public Map<String, Phone> getPhones() {
		return phoneNumbers;
	}
	public void addPhone(String type, Phone phoneNumber) {
		phoneNumbers.put(type, phoneNumber);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%s %s (%s) %s", lastName, firstName, userName,	email));
		if (billingAddress != null) sb.append("; billing address: " + billingAddress);
		
		return sb.toString();
	}

	public void addPaymentData(PaymentData paymentData) {
		this.paymentData.add(paymentData);
	}

	public List<PaymentData> getPaymentData() {
		return paymentData;
	}

	public void addRole(FhBayRoles role) {
		roles.add(role);
	}
	
	public Set<FhBayRoles> getRoles() {
		return roles;
	}
}
