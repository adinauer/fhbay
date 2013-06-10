package at.dinauer.fhbay.web.controller;

import java.util.List;

import org.springframework.ui.Model;

import at.dinauer.fhbay.presentation.PmodArticle;
import at.dinauer.fhbay.presentation.PmodBid;
import at.dinauer.fhbay.presentation.PmodCategory;
import at.dinauer.fhbay.util.DataStore;

public class SpringMvcDataStore implements DataStore {

	private Model model;

	public SpringMvcDataStore(Model model) {
		this.model = model;
	}
	
	public void storeBids(List<PmodBid> bids) {
		model.addAttribute("bids", bids);
	}

	public void storeBreadcrumbs(String breadcrumbs) {
		model.addAttribute("selectedCategoryName", breadcrumbs);
	}

	public void storeCategories(List<PmodCategory> categories) {
		model.addAttribute("categories", categories);		
	}

	public void storeSelectedArticle(PmodArticle selectedArticle) {
		model.addAttribute("selectedArticle", selectedArticle);	
	}

	public void storeArticles(List<PmodArticle> articles) {
		model.addAttribute("articles", articles);
	}
}
