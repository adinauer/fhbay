package at.dinauer.fhbay.presentation;

import java.util.ArrayList;
import java.util.List;

import at.dinauer.fhbay.domain.Category;

public class PmodCategory {

	private Long id;
	private String name;
	private List<PmodCategory> subCategories = new ArrayList<>();
	
	public PmodCategory() {}

	public PmodCategory(Category category) {
		setId(category.getId());
		setName(category.getName());

		for (Category subCategory : category.getSubCategories()) {
			addSubCategory(subCategory);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public String getUrlName() {
		return name.replace(' ' , '-');
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PmodCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<PmodCategory> subCategories) {
		this.subCategories = subCategories;
	}
	
	public void addSubCategory(PmodCategory subCategory) {
		subCategories.add(subCategory);
	}
	
	public void addSubCategory(Category subCategory) {
		subCategories.add(new PmodCategory(subCategory));
	}

}
