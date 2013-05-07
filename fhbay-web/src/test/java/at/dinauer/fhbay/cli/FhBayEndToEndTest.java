package at.dinauer.fhbay.cli;

import static org.junit.Assert.*;

import org.junit.Test;

public class FhBayEndToEndTest {
	private final ApplicationRunner application = new ApplicationRunner();

	@Test
	public void afterAddingACustomerItShouldBeListed() {
		application.addCustomer("joe", "user", "joe.user", "pwd");
		
//		assertThat(articles, containsCustomerWithUsername("joe.user"));
	}

}
