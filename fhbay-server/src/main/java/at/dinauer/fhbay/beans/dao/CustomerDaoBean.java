package at.dinauer.fhbay.beans.dao;


import javax.ejb.Stateless;

import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.dao.CustomerDao;


@Stateless
public class CustomerDaoBean extends AbstractDaoBean<Customer, Long> implements CustomerDao {
}
