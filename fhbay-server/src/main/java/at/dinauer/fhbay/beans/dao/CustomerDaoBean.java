package at.dinauer.fhbay.beans.dao;


import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.dao.CustomerDao;


@Stateless
public class CustomerDaoBean extends AbstractDaoBean<Customer, Long> implements CustomerDao {

	public Customer findByUserName(String userName) {
		TypedQuery<Customer> articlesByPatternQuery = getEntityManager().createQuery("SELECT c FROM Customer c WHERE lower(c.userName) = :userName", Customer.class);
		
		articlesByPatternQuery.setParameter("userName", userName);
		

		Customer customer = null;
		try {
			customer = articlesByPatternQuery.getSingleResult();
		} catch (NoResultException e) {}
		
		return customer;
	}
}
