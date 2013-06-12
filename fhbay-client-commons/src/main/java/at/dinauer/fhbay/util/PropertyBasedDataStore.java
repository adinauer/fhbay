package at.dinauer.fhbay.util;

import java.util.List;

import at.dinauer.fhbay.presentation.PmodArticle;
import at.dinauer.fhbay.presentation.PmodBid;
import at.dinauer.fhbay.presentation.PmodCategory;

public class PropertyBasedDataStore implements DataStore {

	private List<PmodBid> bids;
	private String selectedCategoryName;
	private List<PmodCategory> categories;
	private PmodArticle selectedArticle;
	private List<PmodArticle> articles;
	
	public void storeBids(List<PmodBid> bids) {
		this.bids = bids;
	}
	
	public List<PmodBid> getBids() {
		return bids;
	}

	public void storeBreadcrumbs(String breadcrumbs) {
		this.selectedCategoryName = breadcrumbs;
	}
	
	public String getSelectedCategoryName() {
		return selectedCategoryName;
	}

	public void storeCategories(List<PmodCategory> categories) {
		this.categories = categories;		
	}
	
	public List<PmodCategory> getCategories() {
		return categories;
	}

	public void storeSelectedArticle(PmodArticle selectedArticle) {
		this.selectedArticle = selectedArticle;	
	}
	
	public PmodArticle getSelectedArticle() {
		return selectedArticle;
	}

	public void storeArticles(List<PmodArticle> articles) {
		this.articles = articles;
	}
	
	public List<PmodArticle> getArticles() {
		return articles;
	}
}
