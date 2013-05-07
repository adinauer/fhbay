package sve2.fhbay.beans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import sve2.fhbay.domain.Customer;
import sve2.fhbay.interfaces.SimpleCustomerAdminRemote;
import sve2.fhbay.interfaces.dao.SimpleCustomerDao;

@Stateless
@Remote(SimpleCustomerAdminRemote.class)
public class SimpleCustomerAdminBean implements SimpleCustomerAdminRemote {

	@EJB
	private SimpleCustomerDao customerDao;

	@Override
	public Long saveCustomer(Customer customer) {
		System.out.printf("saveCustomer(%s)%n", customer);
		customerDao.persist(customer);
		return customer.getId();
	}

	@Override
	public Collection<Customer> findAllCustomers() {
		System.out.printf("findAllCustomers%n");
		return customerDao.findAll();
	}

	@Override
	public Customer findCustomerById(Long id) {
		System.out.printf("findCustomerById(%d)%n", id);
		return customerDao.findById(id);
	}

}
