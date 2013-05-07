package at.dinauer.fhbay.interfaces.dao;

import java.util.Collection;

import javax.ejb.Local;

import at.dinauer.fhbay.domain.Customer;


@Local
public interface SimpleCustomerDao {
	void persist(Customer customer);

	Customer findById(Long id);

	Collection<Customer> findAll();
}
