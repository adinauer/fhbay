package at.dinauer.fhbay.presentation;

import org.junit.Test;

import at.dinauer.fhbay.domain.Category;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class PmodCategoryTest {
	@Test
	public void constructingAPresentationModelFromACategoryShouldResultInTheSameValues() {
		Category subCategory = new Category();
		subCategory.setId(987L);
		
		Category category = new Category();
		category.setId(1234L);
		category.setName("Photography");
		category.addSubCategory(subCategory);
		
		PmodCategory pmod = new PmodCategory(category);
		
		assertThat(pmod.getId(), is(equalTo(category.getId())));
		assertThat(pmod.getName(), is(equalTo(category.getName())));
		assertThat(pmod.getSubCategories().get(0).getId(), is(equalTo(987L)));
	}
	
	@Test
	public void replacesBlanksInNameForUrlName() {
		PmodCategory pmod = new PmodCategory();
		pmod.setName("no more spaces");
		
		assertThat(pmod.getUrlName(), is(equalTo("no-more-spaces")));
	}
}
