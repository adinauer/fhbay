package at.dinauer.fhbay.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import at.dinauer.fhbay.domain.Customer;

@Remote
public interface SimpleCustomerAdminRemote {
	Long saveCustomer(Customer customer);

	Collection<Customer> findAllCustomers();

	Customer findCustomerById(Long id);
}
