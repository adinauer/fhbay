package sve2.fhbay.beans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import sve2.fhbay.domain.Customer;
import sve2.fhbay.interfaces.CustomerAdminRemote;
import sve2.fhbay.interfaces.dao.CustomerDao;
import sve2.fhbay.interfaces.exceptions.IdNotFoundException;

@Stateless
@Remote(CustomerAdminRemote.class)
public class CustomerAdminBean implements CustomerAdminRemote {

	@EJB
	private CustomerDao customerDao;

	@Override
	public Long saveCustomer(Customer customer) {
		System.out.printf("saveCustomer(%s)%n", customer);
		customer = customerDao.merge(customer);
		return customer.getId();
	}

	@Override
	public Collection<Customer> findAllCustomers() {
		System.out.printf("findAllCustomers%n");
		return customerDao.findAll();
	}

	@Override
	public Customer findCustomerById(Long id) throws IdNotFoundException {
		System.out.printf("findCustomerById(%d)%n", id);
		Customer customer = customerDao.findById(id);
		if (customer == null) throw new IdNotFoundException(id, "customer");
		return customer;
	}

}
