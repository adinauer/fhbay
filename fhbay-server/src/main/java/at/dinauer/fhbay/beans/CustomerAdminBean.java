package at.dinauer.fhbay.beans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.interfaces.dao.CustomerDao;


@Stateless
public class CustomerAdminBean implements CustomerAdminRemote {

	@EJB
	private CustomerDao customerDao;

	@Override
	public Long saveCustomer(Customer customer) {
		System.out.printf("saveCustomer(%s)%n", customer);
		
		Customer persistedCustomer = customerDao.merge(customer);
		
		return persistedCustomer.getId();
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
