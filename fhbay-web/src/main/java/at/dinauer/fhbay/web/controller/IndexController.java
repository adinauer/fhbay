package at.dinauer.fhbay.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	@RequestMapping(value = {"/", "/index", "/login", "/loginfailed", "/logout"})
	public String showArticles(Model model) {
		model.addAttribute("showArticleList", true);

		return "index";
	}

	@RequestMapping(value = "/article")
	public String showArticleDetails(Model model, @RequestParam("id") String id) {
		System.out.println("showing details for article with id: " + id);
		model.addAttribute("showArticleDetails", true);

		return "index";
	}

	@RequestMapping(value = "/bidHistory")
	public String showBidHistory(Model model, @RequestParam("articleId") String articleId) {
		System.out.println("showing bids for article with id: " + articleId);
		model.addAttribute("showBids", true);

		return "index";
	}

	@RequestMapping(value = "/offerArticle")
	public String showOfferArticleForm(Model model) {
		model.addAttribute("showOfferArticleForm", true);

		return "index";
	}
}
