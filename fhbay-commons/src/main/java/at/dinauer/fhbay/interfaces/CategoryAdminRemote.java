package at.dinauer.fhbay.interfaces;

import java.util.List;

import javax.ejb.Remote;

import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.exceptions.IdNotFoundException;

@Remote
public interface CategoryAdminRemote {
	Long saveCategory(Category category);

	List<Category> findAllCategories();

	Category findCategoryById(Long id) throws IdNotFoundException;

	List<Category> findRootCategories();
}
