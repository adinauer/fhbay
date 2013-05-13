package at.dinauer.fhbay;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import at.dinauer.fhbay.domain.Article;
import at.dinauer.fhbay.domain.Bid;
import at.dinauer.fhbay.domain.Category;
import at.dinauer.fhbay.domain.Customer;

public class FhBayMatchers {
	public static Matcher<Category> aCategoryWithName(String name) {
		return new FeatureMatcher<Category, String>(equalTo(name), "category with name ", "was") {
			protected String featureValueOf(Category actual) {
				return actual.getName();
			}
		};
	}

	public static Matcher<Customer> aCustomerWithUserName(final String userName) {
		return new FeatureMatcher<Customer, String>(equalTo(userName), "customer with username ", "was") {
			protected String featureValueOf(Customer actual) {
				return actual.getUserName();
			}
		};
	}

	public static Matcher<Article> anArticleWithName(final String name) {
		return new FeatureMatcher<Article, String>(equalTo(name), "article with name ", "was") {
			protected String featureValueOf(Article actual) {
				return actual.getName();
			}
		};
	}
	
	public static Matcher<Bid> aBidWithAmount(double amount) {
		return new FeatureMatcher<Bid, Double>(equalTo(amount), "a bid with amount ", "was") {
			protected Double featureValueOf(Bid actual) {
				return actual.getAmount();
			}
		};
	}
}
