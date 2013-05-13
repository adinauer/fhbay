package at.dinauer.fhbay.interfaces.dao;


import java.util.List;

import javax.ejb.Local;

import at.dinauer.fhbay.domain.Category;



@Local
public interface CategoryDao extends Dao<Category, Long> {
	List<Category> findRootCategories();
}
