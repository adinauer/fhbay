package at.dinauer.fhbay;

import static at.dinauer.fhbay.FhBayMatchers.aCustomerWithUserName;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import at.dinauer.fhbay.domain.Address;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.test.categories.IntegrationTest;

@Category(IntegrationTest.class)
public class CustomerRoundTripTest {
	private CustomerAdminRemote customerAdmin;
	
	@Before
	public void lookupCustomerAdminBean() throws Exception {
		customerAdmin = new ServiceLocator().locate(CustomerAdminRemote.class);
	}

	@Before
	public void clearDatabase() throws Exception {
		new DatabaseCleaner().clean();
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
