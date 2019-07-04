package com.coedelsur;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	final static Logger logger = Logger.getLogger(SecurityConfig.class);
	
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/register.xhtml","/recuperar.xhtml","/confirmUser.xhtml","/javax.faces.resource/**").permitAll()
									.antMatchers("/paciente/**").hasRole("PACIENTE")
									.antMatchers("/doctor/**").hasRole("DOCTOR")
									.antMatchers("/","/login.xhtml").permitAll()
									.anyRequest().authenticated()
									.and()
				.formLogin().loginPage("/login.xhtml")
							.successHandler(customizeAuthenticationSuccessHandler)
							.failureUrl("/login.xhtml?authfailed=true")
							.permitAll()
							.and()
				.logout().logoutSuccessUrl("/login.xhtml")
						 .logoutUrl("/j_spring_security_logout")
						 .deleteCookies("auth_code", "JSESSIONID")
						 .clearAuthentication(true)
						 .invalidateHttpSession(true)
						 .and()
				.csrf().disable();
	}
	
}
