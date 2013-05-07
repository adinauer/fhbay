package at.dinauer.fhbay.beans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.SimpleCustomerAdminRemote;
import at.dinauer.fhbay.interfaces.dao.SimpleCustomerDao;


@Stateless
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
