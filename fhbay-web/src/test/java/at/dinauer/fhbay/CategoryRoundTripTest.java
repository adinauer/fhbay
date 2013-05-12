package at.dinauer.fhbay;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.interfaces.CategoryAdminRemote;


public class CategoryRoundTripTest {
	private CategoryAdminRemote categoryAdmin;
	
	@Before
	public void lookupCategoryAdminBean() throws Exception {
		categoryAdmin = new ServiceLocator().locate(CategoryAdminRemote.class);
	}
	
	@Before
	public void clearDatabase() throws Exception {
		new DatabaseCleaner().clean();
	}
	
	@Test
	public void canSaveAndRetrieveCategoryIncludingItsSubcategory() throws Exception {
		Category photography = new Category();
		photography.setName("Photography");
		
		Long id = categoryAdmin.saveCategory(photography);
		photography.setId(id);

		Category cameras = new Category();
		cameras.setName("Cameras");
		cameras.setParent(photography);
		
		categoryAdmin.saveCategory(cameras);
		
		Category persistedCategory = categoryAdmin.findCategoryById(id);
		
		assertThat(persistedCategory.getId(), is(equalTo(photography.getId())));
		assertThat(persistedCategory.getName(), is(equalTo(photography.getName())));
		
		assertThat(persistedCategory.getSubCategories().get(0).getName(), is(equalTo(cameras.getName())));
	}
}
