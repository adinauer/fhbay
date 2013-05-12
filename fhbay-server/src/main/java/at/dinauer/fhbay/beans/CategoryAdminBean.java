package at.dinauer.fhbay.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.CategoryAdminRemote;
import at.dinauer.fhbay.interfaces.dao.CategoryDao;


@Stateless
public class CategoryAdminBean implements CategoryAdminRemote {

	@EJB
	private CategoryDao categoryDao;
	
	public Long saveCategory(Category category) {
		Category persistedCategory = categoryDao.merge(category);
		
		return persistedCategory.getId();
	}

	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

	public Category findCategoryById(Long id) throws IdNotFoundException {
		Category category = categoryDao.findById(id);
		
		if (category == null) throw new IdNotFoundException(id, "category");
		
		return category;
	}
}
