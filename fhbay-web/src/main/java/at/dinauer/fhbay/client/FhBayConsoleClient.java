package at.dinauer.fhbay.client;

import java.util.Collection;
import java.util.Date;
import java.util.Map.Entry;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

import at.dinauer.fhbay.domain.Address;
import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.CreditCard;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.domain.PaymentData;
import at.dinauer.fhbay.domain.Phone;
import at.dinauer.fhbay.exceptions.IdNotFoundException;
import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.util.DateUtil;
import at.dinauer.fhbay.util.JndiUtil;
import at.dinauer.fhbay.util.LoggingUtil;


public class FhBayConsoleClient {

	public static void main(String[] args) throws Exception {
		LoggingUtil.initJdkLogging("logging.properties");

		custAdmin = JndiUtil
				.getRemoteObject(
						"fhbay-ear/fhbay-server/CustomerAdminBean!at.dinauer.fhbay.interfaces.CustomerAdminRemote",
						CustomerAdminRemote.class);
		artAdmin = JndiUtil
				.getRemoteObject(
						"fhbay-ear/fhbay-server/ArticleAdminBean!at.dinauer.fhbay.interfaces.ArticleAdminRemote",
						ArticleAdminRemote.class);

		System.out.println("### testing customer admin ...");
		testCustomerAdmin();

		System.out.println("### testing article admin ...");
		testArticleAdmin();
		
		System.out.println("### testing article processor ...");
		testArticleProcessor();
	}

	private static CustomerAdminRemote custAdmin;

	private static void testCustomerAdmin() {
		try {
			System.out.println("--------------- save customer ---------------");
			Customer cust1 = new Customer("Jaquira", "Hummelbrunner", "jaqui",
					"pwd", "Johann.Heinzelreiter@fh-hagenberg.at");
			cust1.setBillingAddress(new Address("4232", "Hagenberg",
					"Hauptstrasse 117"));
			cust1.addPhone("mobile", new Phone("+43", "(0) 555 333"));
			// cust1.addPhone(new Phone("mobile", "+43", "(0) 555 333"));
			cust1.addShippingAddress(new Address("5555", "Mostbusch",
					"Linzerstrasse 15"));
			cust1.addPaymentData(new CreditCard("Himmelbrunner", "010448812",
					DateUtil.getDate(2007, 07, 1)));

			Customer cust2 = new Customer("Maggi", "Weibold", "maggi",
					"Johann.Heinzelreiter@fh-hagenberg.at", "wei");
			cust2.setBillingAddress(new Address("4020", "Linz",
					"Hauptstrasse 117"));
			cust2.addShippingAddress(new Address("8050", "Koenigsbrunn",
					"Maisfeld 15"));

			System.out
					.println("--------------- saveOrUpdateCustomer ---------------");

			Long cust1Id = custAdmin.saveCustomer(cust1);
			@SuppressWarnings("unused")
			Long cust2Id = custAdmin.saveCustomer(cust2);

			System.out
					.println("--------------- addShippingAddress ---------------");
			cust1 = custAdmin.findCustomerById(cust1Id);
			cust1.addShippingAddress(new Address("1000", "Wien",
					"Haudumgasse 87a"));
			cust1.addShippingAddress(new Address("5000", "Salzburg",
					"Moritzwinkel 5"));
			custAdmin.saveCustomer(cust1);

			System.out
					.println("--------------- findAllCustomers ---------------");
			for (Customer c : custAdmin.findAllCustomers()) {
				System.out.println(c);

				if (!c.getPhones().isEmpty()) {
					System.out.println("  phone numbers:");
					// for (Phone phone : c.getPhones())
					// System.out.println("     " + phone);
					for (Entry<String, Phone> entry : c.getPhones().entrySet())
						System.out.println("     " + entry.getKey() + ": "
								+ entry.getValue());
				}
				if (!c.getShippingAddresses().isEmpty()) {
					System.out.println("  shipping addresses:");
					for (Address a : c.getShippingAddresses())
						System.out.println("     " + a);
				}
				if (!c.getPaymentData().isEmpty()) {
					System.out.println("  payment data:");
					for (PaymentData pd : c.getPaymentData())
						System.out.println("     " + pd);
				}
			}

			System.out
					.println("--------------- findCustomerById ---------------");
			System.out.println(custAdmin.findCustomerById(cust1.getId()));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static ArticleAdminRemote artAdmin;

	private static void testArticleAdmin() throws NamingException {
		Customer[] customers = custAdmin.findAllCustomers().toArray(
				new Customer[] {});
		if (customers.length <= 1)
			return;
		Long cust1Id = customers[0].getId();
		Long cust2Id = customers[1].getId();

		System.out.println("------------- saveArticle ----------------------");

		try {
			Date now = DateUtil.now();
			Article art1 = new Article("DDR2 ECC",
					"Neuwertiger Speicherbaustein", 100, now,
					DateUtil.addSeconds(now, 3));
			Long art1Id = artAdmin.offerArticle(art1, cust1Id);

			Article art2 = new Article("Samsung T166",
					"Samsung Spinpoint T166, 500GB, SATA", 150.99, now,
					DateUtil.addSeconds(now, 4));
			artAdmin.offerArticle(art2, cust2Id);

			Article art3 = new Article("OCZ Agility 2 T166",
					"SSD mit neuartiger Flash-Technologie", 768.99, now,
					DateUtil.addSeconds(now, 5));
			artAdmin.offerArticle(art3, cust2Id);

			System.out
					.println("------------- findArticleById ------------------");
			System.out.printf("art1=%s\n", artAdmin.findArticleById(art1Id));

			System.out
					.println("------------- findAllMatchingArticles ----------");
			System.out.println("Articles matching \"neu\"");
			Collection<Article> matchingArticles = artAdmin
					.findAllMatchingArticles(null, "neu", true);
			if (matchingArticles != null && !matchingArticles.isEmpty())
				for (Article art : matchingArticles)
					System.out.printf("%s - %s%n", art, art.getDescription());
			else
				System.out.println("No matching artilces found.");
		} catch (IdNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void articleToMessage(MapMessage msg, Article article)
			throws JMSException {
		msg.setString("name", article.getName());
		msg.setString("description", article.getDescription());
		msg.setDouble("initialPrice", article.getInitialPrice());
		msg.setLong("startDate", article.getStartDate().getTime());
		msg.setLong("endDate", article.getStartDate().getTime());
	}

	// add user (ApplicationRealm):
	// username=jboss,
	// password=ejb,
	// group=guest
	private static void testArticleProcessor() throws NamingException {
		String username = JndiUtil.getProperty(Context.SECURITY_PRINCIPAL);
		String password = JndiUtil.getProperty(Context.SECURITY_CREDENTIALS);

		try {
			Customer[] customers = custAdmin.findAllCustomers().toArray(
					new Customer[] {});
			if (customers.length == 0)
				return;
			Long sellerId = customers[0].getId();

			// java:jboss/exported/jms/RemoteConnectionFactory
			ConnectionFactory connectionFactory = JndiUtil.getRemoteObject("jms/RemoteConnectionFactory", ConnectionFactory.class);
			// java:jboss/exported/jms/queue/FhBayQueue
			Queue fhBayQueue = JndiUtil.getRemoteObject("jms/queue/FhBayQueue", Queue.class);
			
			Connection connection = connectionFactory.createConnection(username, password);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(fhBayQueue);
			
			for (int i = 1; i < 30; i++) {
				Article article = new Article("Article-" + i, "Description-" + i, 1000.0 + (i * 100), DateUtil.now(), DateUtil.addSeconds(DateUtil.now(), 5+i));
				
				MapMessage articleMessage = session.createMapMessage();
				articleToMessage(articleMessage, article);
				articleMessage.setLong("sellerId", sellerId);
				
				producer.send(articleMessage);
			}
			
			connection.close();
			session.close();
			producer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}