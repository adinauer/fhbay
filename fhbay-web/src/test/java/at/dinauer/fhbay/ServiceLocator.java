package at.dinauer.fhbay;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import at.dinauer.fhbay.interfaces.ArticleAdminRemote;
import at.dinauer.fhbay.interfaces.AuctionRemote;
import at.dinauer.fhbay.interfaces.CategoryAdminRemote;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;
import at.dinauer.fhbay.util.JndiUtil;

public class ServiceLocator {
	public <T> T locate(Class<T> serviceClass) throws NamingException {
		return JndiUtil.getRemoteObject(getServiceName(serviceClass), serviceClass);
	}

	private String getServiceName(Class<?> serviceClass) {
		Map<Class<?>, String> serviceNames = new HashMap<>();
		
		serviceNames.put(ArticleAdminRemote.class, "fhbay-ear/fhbay-server/ArticleAdminBean!at.dinauer.fhbay.interfaces.ArticleAdminRemote");
		serviceNames.put(CustomerAdminRemote.class, "fhbay-ear/fhbay-server/CustomerAdminBean!at.dinauer.fhbay.interfaces.CustomerAdminRemote");
		serviceNames.put(CategoryAdminRemote.class, "fhbay-ear/fhbay-server/CategoryAdminBean!at.dinauer.fhbay.interfaces.CategoryAdminRemote");
		serviceNames.put(AuctionRemote.class, "fhbay-ear/fhbay-server/AuctionBean!at.dinauer.fhbay.interfaces.AuctionRemote");
		
		return serviceNames.get(serviceClass);
	}
}