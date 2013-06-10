package at.dinauer.fhbay.util;

import java.util.List;

import at.dinauer.fhbay.presentation.PmodArticle;
import at.dinauer.fhbay.presentation.PmodBid;
import at.dinauer.fhbay.presentation.PmodCategory;

public interface DataStore {
	public void storeBids(List<PmodBid> bids);

	public void storeBreadcrumbs(String breadcrumbs);

	public void storeCategories(List<PmodCategory> categories);

	public void storeSelectedArticle(PmodArticle selectedArticle);

	public void storeArticles(List<PmodArticle> articles);
}
