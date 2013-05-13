package at.dinauer.fhbay.interfaces.dao;


import javax.ejb.Local;

import at.dinauer.fhbay.domain.Customer;



@Local
public interface CustomerDao extends Dao<Customer, Long> {

	Customer findByUserName(String userName);
}
