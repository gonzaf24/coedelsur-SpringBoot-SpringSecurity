package com.coedelsur;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
 
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
 	final static Logger logger = Logger.getLogger(CustomizeAuthenticationSuccessHandler.class);

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info("autenticando .... !");
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_DOCTOR".equals(auth.getAuthority())){
            	logger.info("inicio como Doctor");
                response.sendRedirect("/doctor/inicio.xhtml");
            }else if ("ROLE_PACIENTE".equals(auth.getAuthority())){
          	 	 logger.info("inicio como Paciente");
                 response.sendRedirect("/paciente/inicioPaciente.xhtml");
               }
        }
  }
}