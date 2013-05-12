package at.dinauer.fhbay;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Address;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.util.JndiUtil;


public class CustomerRoundTripTest {
	private CustomerAdminRemote customerAdmin;
	
	@Before
	public void lookupCustomerAdminBean() throws Exception {
		customerAdmin = JndiUtil.getRemoteObject(
			"fhbay-ear/fhbay-server/CustomerAdminBean!at.dinauer.fhbay.interfaces.CustomerAdminRemote",
			CustomerAdminRemote.class);
	}
	
	@Before
	public void clearDatabase() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:13306/fhbay", "fhbay", "pwd");

        conn.createStatement().execute("SET FOREIGN_KEY_CHECKS=0");
        conn.createStatement().execute("TRUNCATE TABLE Customer");
        conn.createStatement().execute("SET FOREIGN_KEY_CHECKS=1");
        
        conn.close();
	}
	
	@Test
	public void canSaveAndRetrieveCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setUserName("joe.user");
		customer.setFirstName("Joe");
		customer.setLastName("User");
		customer.setEmail("joe.user@example.com");
		customer.setPassword("pwd");
		
		customer.setBillingAddress(new Address("8010", "Graz", "Trieserstrasse 1"));
		customer.setShippingAddress(new Address("4010", "Linz", "Hauptplatz 1"));
		
		Long id = customerAdmin.saveCustomer(customer);
		customer.setId(id);

		Customer persistedCustomer = customerAdmin.findCustomerById(id);
		
		assertSameCustomer(customer, persistedCustomer);
	}
	
	@Test
	public void canSaveMultipleCustomersAndRetrieveAllOfThem() {
		Customer roryGallagher = new Customer();
		roryGallagher.setUserName("rory.gallagher");
		
		Customer warrenZevon = new Customer();
		warrenZevon.setUserName("warren.zevon");
		
		customerAdmin.saveCustomer(roryGallagher);
		customerAdmin.saveCustomer(warrenZevon);
		
		Collection<Customer> allCustomers = customerAdmin.findAllCustomers();
		
		assertThat(allCustomers, containsInAnyOrder(
				aCustomerWithUserName("rory.gallagher"), 
				aCustomerWithUserName("warren.zevon")));
	}

	private Matcher<Customer> aCustomerWithUserName(final String userName) {
		return new FeatureMatcher<Customer, String>(equalTo(userName), "customer with username ", "was") {
			protected String featureValueOf(Customer actual) {
				return actual.getUserName();
			}
		};
	}

	private void assertSameCustomer(Customer expectedCustomer, Customer actualCustomer) {
		assertThat(actualCustomer.getId(), is(equalTo(expectedCustomer.getId())));
		assertThat(actualCustomer.getUserName(), is(equalTo(expectedCustomer.getUserName())));
		assertThat(actualCustomer.getFirstName(), is(equalTo(expectedCustomer.getFirstName())));
		assertThat(actualCustomer.getLastName(), is(equalTo(expectedCustomer.getLastName())));
		assertThat(actualCustomer.getEmail(), is(equalTo(expectedCustomer.getEmail())));
		assertThat(actualCustomer.getPassword(), is(equalTo(expectedCustomer.getPassword())));
		
		assertSameAddress(expectedCustomer.getBillingAddress(), actualCustomer.getBillingAddress());
		assertSameAddress(expectedCustomer.getShippingAddress(), actualCustomer.getShippingAddress());
	}

	private void assertSameAddress(Address expectedAddress, Address actualAddress) {
		assertThat(actualAddress.getCity(), is(equalTo(expectedAddress.getCity())));
		assertThat(actualAddress.getStreet(), is(equalTo(expectedAddress.getStreet())));
		assertThat(actualAddress.getZipCode(), is(equalTo(expectedAddress.getZipCode())));
	}
}
