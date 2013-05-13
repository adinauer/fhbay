package at.dinauer.fhbay.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import at.dinauer.fhbay.ServiceLocator;
import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.CategoryAdminRemote;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.util.DateUtil;

@Controller
public class TestController {

	private ServiceLocator serviceLocator = new ServiceLocator();
	
	@RequestMapping(value = "/insertTestData")
	public String insertTestData() throws Exception {
		System.out.println("inserting test data ...");
		
	/*****************
	 * insert categories
	 ******************/
		CategoryAdminRemote categoryAdmin = serviceLocator.locate(CategoryAdminRemote.class);
		
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
		
		categoryAdmin.saveCategory(photography);
		categoryAdmin.saveCategory(software);
		categoryAdmin.saveCategory(audio);
		categoryAdmin.saveCategory(video);
		
		List<Category> categories = categoryAdmin.findAllCategories();
		

	/*****************
	 * insert customers
	 ******************/
		CustomerAdminRemote customerAdmin = serviceLocator.locate(CustomerAdminRemote.class);
		
		Customer seller1 = new Customer();
		
		seller1.setFirstName("Tom");
		seller1.setLastName("Seller");
		seller1.setUserName("tom.seller");
		seller1.setId(customerAdmin.saveCustomer(seller1));
		Customer seller = seller1;
		
		
	/*****************
	 * insert articles
	 ******************/
		ArticleAdminRemote articleAdmin = serviceLocator.locate(ArticleAdminRemote.class);
		
		Article canonEos1Dx = new Article("Canon EOS 1D X (SLR) Body", "bla bla", 6499.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 50));
		Article canonEos7D = new Article("Canon EOS 7D (SLR) Body", "bla blub", 1199.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 100));
		Article canonEos60D = new Article("Canon EOS 60D (SLR) Body", "bla blub bla", 799.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 200));
		Article canonEos500D = new Article("Canon EOS 500D (SLR) Body", "bla blub blub", 499.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 600));
		Article canonEos1000D = new Article("Canon EOS 1000D (SLR) Body", "bla blub blub", 399.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), -60));
		Article sonyKD84X = new Article("Sony KD-84X9005", "television", 24990.00, DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 3600));

		canonEos1Dx.setId(articleAdmin.offerArticle(canonEos1Dx, seller.getId()));
		canonEos7D.setId(articleAdmin.offerArticle(canonEos7D, seller.getId()));
		canonEos60D.setId(articleAdmin.offerArticle(canonEos60D, seller.getId()));
		canonEos500D.setId(articleAdmin.offerArticle(canonEos500D, seller.getId()));
		canonEos1000D.setId(articleAdmin.offerArticle(canonEos1000D, seller.getId()));
		sonyKD84X.setId(articleAdmin.offerArticle(sonyKD84X, seller.getId()));

		Long camerasId = -1L;
		Long tvsId = -1L;
		
		for (Category category : categories) {
			if (category.getName().equals("Cameras")) camerasId = category.getId();
			if (category.getName().equals("TVs")) tvsId = category.getId();
		}
		
		articleAdmin.assignArticleToCategory(canonEos1Dx.getId(), camerasId);
		articleAdmin.assignArticleToCategory(canonEos7D.getId(), camerasId);
		articleAdmin.assignArticleToCategory(canonEos60D.getId(), camerasId);
		articleAdmin.assignArticleToCategory(canonEos500D.getId(), camerasId);
		articleAdmin.assignArticleToCategory(canonEos1000D.getId(), camerasId);
		articleAdmin.assignArticleToCategory(sonyKD84X.getId(), tvsId);
		
		return "redirect:/";
	}
}
