package at.dinauer.fhbay;

import static at.dinauer.fhbay.FhBayMatchers.aCategoryWithName;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.interfaces.CategoryAdminRemote;
import at.dinauer.fhbay.test.categories.IntegrationTest;

@org.junit.experimental.categories.Category(IntegrationTest.class)
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
		assertThat(rootCategories, not(contains(FhBayMatchers.aCategoryWithName("Cameras"))));
	}

	private Category categoryWithName(String name) {
		Category category = new Category();
		
		category.setName(name);
		
		return category;
	}
}
