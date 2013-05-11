package at.dinauer.fhbay.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.presentation.PmodArticle;
import at.dinauer.fhbay.presentation.PmodCategory;
import at.dinauer.fhbay.util.DateUtil;

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
		
		PmodArticle selectedArticle = new PmodArticle();
		selectedArticle.setId(123456L);
		selectedArticle.setName("Nikon D40 (SLR) Body");
		selectedArticle.setDescription("abcdefg");
		selectedArticle.setInitialPrice(599.00);
		selectedArticle.setStartDate(DateUtil.addSeconds(DateUtil.now(), -30));
		selectedArticle.setEndDate(DateUtil.addSeconds(DateUtil.now(), -600));
		
		model.addAttribute("selectedArticle", selectedArticle);
		
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
		fetchArticles(model);
		
		model.addAttribute("currentDateAndTime", DateUtil.now());
		SimpleDateFormat dateAndTimeFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
		model.addAttribute("dateAndTimeFormat", dateAndTimeFormat);
	}

	private void fetchArticles(Model model) {
		List<PmodArticle> articles = new ArrayList<>();
		
		Article canonEos1Dx = new Article("Canon EOS 1D X (SLR) Body", "bla bla", 6499.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 50));
		Article canonEos7D = new Article("Canon EOS 7D (SLR) Body", "bla blub", 1199.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 100));
		Article canonEos60D = new Article("Canon EOS 60D (SLR) Body", "bla blub bla", 799.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 200));
		Article canonEos500D = new Article("Canon EOS 500D (SLR) Body", "bla blub blub", 499.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 600));
		Article canonEos1000D = new Article("Canon EOS 1000D (SLR) Body", "bla blub blub", 399.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), -60));
		
		canonEos1Dx.setId(12345L);
		
		articles.add(new PmodArticle(canonEos1Dx));
		articles.add(new PmodArticle(canonEos7D));
		articles.add(new PmodArticle(canonEos60D));
		articles.add(new PmodArticle(canonEos500D));
		articles.add(new PmodArticle(canonEos1000D));
		
		model.addAttribute("articles", articles);
	}

	private void fetchCategories(Model model) {
		List<PmodCategory> categories = new ArrayList<>();
		
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
		
		categories.add(new PmodCategory(photography));
		categories.add(new PmodCategory(software));
		categories.add(new PmodCategory(audio));
		categories.add(new PmodCategory(video));
		
		model.addAttribute("categories", categories);
	}
}
