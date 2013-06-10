package at.dinauer.fhbay.security;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import at.dinauer.fhbay.util.ServiceLocator;
import at.dinauer.fhbay.domain.Customer;
import at.dinauer.fhbay.interfaces.CustomerAdminRemote;


public class FhBayAuthenticationProvider implements AuthenticationProvider  {
	
	private PasswordEncoder passwordEncoder = new ShaPasswordEncoder();
	private ServiceLocator serviceLocator = new ServiceLocator();

	
	public Authentication authenticate(Authentication incomingAuthentication)
			throws AuthenticationException {

		String username = String.valueOf(incomingAuthentication.getPrincipal());
		String password = String.valueOf(incomingAuthentication.getCredentials());
		if (password != null) {
			System.out.println("trying to log in with username: " + username + " and password: " + password.substring(0, 1) + "***");
		} else {
			System.out.println("trying to log in with username: " + username + " and password: " + password);
		}
		
		Authentication outgoingAuthentication = doAuthentication(incomingAuthentication);

		System.out.println("is authenticated: " + outgoingAuthentication.isAuthenticated());
		System.out.println("granted roles: " + outgoingAuthentication.getAuthorities());
		
		return outgoingAuthentication;
	}

	private Authentication doAuthentication(
			Authentication incomingAuthentication) {
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
		
		return processedAuthentication;
	}

	private boolean isPasswordIncorrect(String password, Customer customer) {
		return !isPasswordCorrect(password, customer);
	}

	private boolean isPasswordCorrect(String password, Customer customer) {
		return passwordEncoder.isPasswordValid(customer.getPassword(), password, User.PASSWORD_SALT);
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}
