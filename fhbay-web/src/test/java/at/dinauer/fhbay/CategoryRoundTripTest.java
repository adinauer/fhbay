package at.dinauer.fhbay;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.domain.Customer;
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
		Category photography = categoryWithName("Photography");
		
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
	
	@Test
	public void canRetrieveOnlyRootCategories() throws Exception {
		Category photography = categoryWithName("Photography");
		Category audio = categoryWithName("Audio");
		Category cameras = categoryWithName("Cameras");
		
		audio.setId(categoryAdmin.saveCategory(audio));
		photography.setId(categoryAdmin.saveCategory(photography));
		cameras.setParent(photography);
		cameras.setId(categoryAdmin.saveCategory(cameras));
		
		List<Category> rootCategories = categoryAdmin.findRootCategories();
		
		assertThat(rootCategories, containsInAnyOrder(
				aCategoryWithName("Photography"),
				aCategoryWithName("Audio")));
		assertThat(rootCategories, not(contains(aCategoryWithName("Cameras"))));
	}

	private Matcher<Category> aCategoryWithName(String name) {
		return new FeatureMatcher<Category, String>(equalTo(name), "category with name ", "was") {
			protected String featureValueOf(Category actual) {
				return actual.getName();
			}
		};
	}

	private Category categoryWithName(String name) {
		Category category = new Category();
		
		category.setName(name);
		
		return category;
	}
}
