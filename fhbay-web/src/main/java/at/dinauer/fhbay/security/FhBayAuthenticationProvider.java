package at.dinauer.fhbay.security;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import at.dinauer.fhbay.ServiceLocator;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;


public class FhBayAuthenticationProvider implements AuthenticationProvider  {
	
	ServiceLocator serviceLocator = new ServiceLocator();

	public Authentication authenticate(Authentication incomingAuthentication)
			throws AuthenticationException {
		String username = String.valueOf(incomingAuthentication.getPrincipal());
		String password = String.valueOf(incomingAuthentication.getCredentials());
		
		Authentication processedAuthentication = null;
		try {
			CustomerAdminRemote customerAdmin = serviceLocator.locate(CustomerAdminRemote.class);
			Customer customer = customerAdmin.findCustomerByUserName(username);
			
			if (customer == null) {
				System.out.println("No customer found with username: " + username);
				return incomingAuthentication;
			}
			
			if (isPasswordIncorrect(password, customer)) {
				System.out.println("Incorrect password!");
				return incomingAuthentication;
			}
			
			User user = new User();
			user.setId(customer.getId());
			user.setUsername(customer.getUserName());
			user.setFirstName(customer.getFirstName());
			user.setLastName(customer.getLastName());
			
			List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
			for (FhBayRoles role : customer.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
			}
			
			processedAuthentication = new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println(format("user: %s; pwd: ***, ok? %s", username, processedAuthentication.isAuthenticated()));
		System.out.println("authorities: " + processedAuthentication.getAuthorities());
		
		return processedAuthentication;
	}

	private boolean isPasswordIncorrect(String password, Customer customer) {
		if (customer.getPassword() == null) return false;
		
		return !customer.getPassword().equals(password);
	}

	public boolean supports(Class<?> clazz) {
		System.out.println("supports? " + clazz);
		return true;
	}

}
