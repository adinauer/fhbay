package at.dinauer.fhbay.security;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class FhBayAuthenticationProvider implements AuthenticationProvider  {

	public Authentication authenticate(Authentication incomingAuthentication)
			throws AuthenticationException {
		String username = String.valueOf(incomingAuthentication.getPrincipal());
		String password = String.valueOf(incomingAuthentication.getCredentials());
		
		System.out.println(format("user: %s; pwd: %s, ok? %s", username, password, incomingAuthentication.isAuthenticated()));

		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(FhBayRoles.ROLE_USER.toString()));
		
		Authentication processedAuthentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
		System.out.println(format("user: %s; pwd: %s, ok? %s", username, password, processedAuthentication.isAuthenticated()));
		System.out.println("authorities: " + processedAuthentication.getAuthorities());
		
		return processedAuthentication;
	}

	public boolean supports(Class<?> clazz) {
		System.out.println("supports? " + clazz);
		return true;
	}

}
