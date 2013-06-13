package at.dinauer.fhbay.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.dinauer.fhbay.domain.BidInfo;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.presentation.PmodBid;
import at.dinauer.fhbay.util.DataFetcher;
import at.dinauer.fhbay.util.PropertyBasedDataStore;
import at.dinauer.fhbay.util.ServiceLocator;

@Controller
public class ApiController {

	private DataFetcher dataFetcher;
	
	private ServiceLocator serviceLocator;
	private AuctionRemote auction;
	
	public ApiController() throws Exception {
		dataFetcher = new DataFetcher();
		
		serviceLocator = new ServiceLocator();
		
		auction = serviceLocator.locate(AuctionRemote.class);
	}
	
	@RequestMapping("/api/bidHistory/{articleId}")
	@ResponseBody
	public List<PmodBid> showArticles(@PathVariable("articleId") String articleId) throws Exception {
		PropertyBasedDataStore dataStore = new PropertyBasedDataStore();
		
		dataFetcher.fetchBids(dataStore, Long.parseLong(articleId));
		
		return dataStore.getBids();
	}
	
	@RequestMapping(value="/api/placeBid", method=RequestMethod.POST)
	@ResponseBody
	public BidInfo placeBid(@RequestBody Map<String, Object> json) throws Exception {
		System.out.println("JSON: " + json);
		
		Long articleId = MapUtils.getLong(json, "articleId");
		Long customerId = MapUtils.getLong(json, "customerId");
		double amount = MapUtils.getDouble(json, "amount");
		
		BidInfo bidInfo = auction.placeBid(articleId, customerId, amount);
		
		return bidInfo;
	}
}
