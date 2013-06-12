package at.dinauer.fhbay.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.presentation.PmodBid;
import at.dinauer.fhbay.util.DataFetcher;
import at.dinauer.fhbay.util.PropertyBasedDataStore;
import at.dinauer.fhbay.util.ServiceLocator;

@Controller
public class ApiController {

	private DataFetcher dataFetcher;
	
	private ServiceLocator serviceLocator;
	private ArticleAdminRemote articleAdmin;
	private AuctionRemote auction;
	
	public ApiController() throws Exception {
		dataFetcher = new DataFetcher();
		
		serviceLocator = new ServiceLocator();
		
		articleAdmin = serviceLocator.locate(ArticleAdminRemote.class);
		auction = serviceLocator.locate(AuctionRemote.class);
	}
	
	@RequestMapping("api/bidHistory/{articleId}")
	@ResponseBody
	public List<PmodBid> showArticles(@PathVariable("articleId") String articleId) throws Exception {
		PropertyBasedDataStore dataStore = new PropertyBasedDataStore();
		
		dataFetcher.fetchBids(dataStore, Long.parseLong(articleId));
		
		return dataStore.getBids();
	}
}
