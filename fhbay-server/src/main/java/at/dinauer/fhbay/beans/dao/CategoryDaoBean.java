package at.dinauer.fhbay.beans.dao;


import java.util.List;

import javax.ejb.Stateless;

import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.interfaces.dao.CategoryDao;


@Stateless
public class CategoryDaoBean extends AbstractDaoBean<Category, Long> implements CategoryDao {

	public List<Category> findRootCategories() {
		return getEntityManager().createQuery("SELECT c FROM Category c WHERE c.parent IS NULL").getResultList();
	}
}