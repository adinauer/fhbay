package at.dinauer.fhbay.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.dinauer.fhbay.domain.Category;

@Controller
public class IndexController {

	@RequestMapping(value = {
			"/", 
			"/login", 
			"/loginfailed", 
			"/logout"})
	public String showArticles(Model model) {
		model.addAttribute("showArticleList", true);

		fetchDomainData(model);
		
		return "index";
	}

	@RequestMapping(value = {
			"/article/{articleId}/{name}", 
			"/article/{articleId}"})
	public String showArticleDetails(Model model, @PathVariable("articleId") String articleId) {
		System.out.println("showing details for article with id: " + articleId);
		model.addAttribute("showArticleDetails", true);
		
		fetchDomainData(model);

		return "index";
	}

	@RequestMapping(value = {
			"/category/{categoryId}/{categoryName}/{subCategoryName}", 
			"/category/{categoryId}/{name}", 
			"/category/{categoryId}"})
	public String showArticlesInCategory(Model model, @PathVariable("categoryId") String categoryId) {
		System.out.println("showing articles in category with id: " + categoryId);
		model.addAttribute("showArticleList", true);
		
		fetchDomainData(model);

		return "index";
	}

	@RequestMapping(value = "/bidHistory")
	public String showBidHistory(Model model, @RequestParam("articleId") String articleId) {
		System.out.println("showing bids for article with id: " + articleId);
		model.addAttribute("showBids", true);
		
		fetchDomainData(model);

		return "index";
	}

	@RequestMapping(value = "/offerArticle")
	public String showOfferArticleForm(Model model) {
		model.addAttribute("showOfferArticleForm", true);

		fetchDomainData(model);
		
		return "index";
	}

	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam("q") String searchString) {
		System.out.println("searching for articles: " + searchString);
		model.addAttribute("showArticleList", true);
		model.addAttribute("searchString", searchString);

		fetchDomainData(model);
		
		return "index";
	}

	private void fetchDomainData(Model model) {
		fetchCategories(model);
	}

	private void fetchCategories(Model model) {
		List<Category> categories = new ArrayList<>();
		
		Category photography = new Category("Photography");
		photography.setId(1L);
			Category cameras = new Category("Cameras");
			cameras.setId(2L);
			Category lenses = new Category("Lenses");
			Category memoryCards = new Category("Memory Cards");
			Category batteries = new Category("Batteries");
			
			photography.addSubCategory(cameras);
			photography.addSubCategory(lenses);
			photography.addSubCategory(memoryCards);
			photography.addSubCategory(batteries);
			
		Category software = new Category("Software");
		
		Category audio = new Category("Audio");
			Category receiver = new Category("Receiver");
			Category speakers = new Category("Speakers");
			Category audiocables = new Category("Cables");
			
			audio.addSubCategory(receiver);
			audio.addSubCategory(speakers);
			audio.addSubCategory(audiocables);
		
		Category video = new Category("Video");
			Category tvs = new Category("TVs");
			Category videoCables = new Category("Cables");
			Category recorder = new Category("Recorder");
			
			video.addSubCategory(tvs);
			video.addSubCategory(videoCables);
			video.addSubCategory(recorder);
		
		categories.add(photography);
		categories.add(software);
		categories.add(audio);
		categories.add(video);
		
		model.addAttribute("categories", categories);
	}
}
