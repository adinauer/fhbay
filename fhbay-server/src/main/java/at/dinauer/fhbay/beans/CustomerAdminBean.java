package at.dinauer.fhbay.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.interfaces.dao.CustomerDao;


@Stateless
public class CustomerAdminBean implements CustomerAdminRemote {

	@EJB
	private CustomerDao customerDao;

	public Long saveCustomer(Customer customer) {
		Customer persistedCustomer = customerDao.merge(customer);
		
		return persistedCustomer.getId();
	}

	public List<Customer> findAllCustomers() {
		return customerDao.findAll();
	}

	public Customer findCustomerById(Long id) throws IdNotFoundException {
		Customer customer = customerDao.findById(id);
		if (customer == null) throw new IdNotFoundException(id, "customer");
		return customer;
	}

	public Customer findCustomerByUserName(String userName) {
		return customerDao.findByUserName(userName);
	}

}
