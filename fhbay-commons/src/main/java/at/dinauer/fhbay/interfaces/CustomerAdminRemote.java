package at.dinauer.fhbay.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;

@Remote
public interface CustomerAdminRemote {
	Long saveCustomer(Customer customer);

	Collection<Customer> findAllCustomers();

	Customer findCustomerById(Long id) throws IdNotFoundException;
}
