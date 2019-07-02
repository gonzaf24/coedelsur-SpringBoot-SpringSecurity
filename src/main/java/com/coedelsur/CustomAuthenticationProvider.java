package com.coedelsur;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	final static Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String user = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = UserDetailService.loadUserByUsername(user.toLowerCase());
	    if (userDetails != null ) {
	    	if(password.equals(userDetails.getPassword())){
	    		
        		return new UsernamePasswordAuthenticationToken(user, password, userDetails.getAuthorities());
			}else {
		    	throw new BadCredentialsException("Contraseña incorrecta");
	    	}
	    }else {
	    	throw new BadCredentialsException("No existe usuario");
	    }
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {

		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

	}
	
}
