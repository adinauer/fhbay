package sve2.fhbay.client;

import javax.naming.InitialContext;

import sve2.fhbay.domain.Customer;
import sve2.fhbay.interfaces.SimpleCustomerAdminRemote;
import sve2.util.LoggingUtil;

public class FhBayConsoleClient {

	public static void main(String[] args) throws Exception {
		LoggingUtil.initJdkLogging("logging.properties");
		testSimpleCustomerAdmin();
	}

	private static void testSimpleCustomerAdmin() throws Exception {
		InitialContext ctx = new InitialContext();
		Object ref = ctx.lookup("fhbay-beans/SimpleCustomerAdminBean!sve2.fhbay.interfaces.SimpleCustomerAdminRemote");
		SimpleCustomerAdminRemote customerAdminProxy = (SimpleCustomerAdminRemote) ref;
		
		Customer fklammer = new Customer("Franz", "Klammer", "fklammer", "fklammer@example.com", "geheim");
		
		System.out.println("client: saveCustomer");
		customerAdminProxy.saveCustomer(fklammer);
	}

}
