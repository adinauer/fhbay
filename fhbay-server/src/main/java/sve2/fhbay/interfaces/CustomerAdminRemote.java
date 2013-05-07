package sve2.fhbay.interfaces;

import java.util.Collection;

import sve2.fhbay.domain.Customer;
import sve2.fhbay.interfaces.exceptions.IdNotFoundException;

public interface CustomerAdminRemote {
	Long saveCustomer(Customer customer);

	Collection<Customer> findAllCustomers();

	Customer findCustomerById(Long id) throws IdNotFoundException;
}
