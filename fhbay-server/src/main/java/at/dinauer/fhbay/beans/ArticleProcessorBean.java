package at.dinauer.fhbay.beans;

import java.util.Date;
import java.util.Random;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.interfaces.ArticleAdminLocal;


@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/FhBayQueue") })
public class ArticleProcessorBean implements MessageListener {

	@EJB
	private ArticleAdminLocal articleAdmin;
	
	private Random random = new Random();
	
	public void onMessage(Message message) {
		MapMessage articleMessage = (MapMessage) message;
		
		try {
			Article article = messageToArticle(articleMessage);
			Long sellerId = articleMessage.getLong("sellerId");
//			Long categoryId = articleMessage.getLong("categoryId");
			
			System.out.println("-----> starting processing article: " + article.getName());
			Thread.sleep(5000 + random.nextInt(3000));
			
			articleAdmin.offerArticle(article, sellerId);
			System.out.println("<----- finished processing article: " + article.getName());
			
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}

	private Article messageToArticle(MapMessage msg) throws JMSException {
		Article article = new Article();
		
		article.setName(msg.getString("name"));
		article.setDescription(msg.getString("description"));
		article.setStartDate(new Date(msg.getLong("startDate")));
		article.setEndDate(new Date(msg.getLong("endDate")));
		article.setInitialPrice(msg.getDouble("initialPrice"));
		
		return article;
	}
}
