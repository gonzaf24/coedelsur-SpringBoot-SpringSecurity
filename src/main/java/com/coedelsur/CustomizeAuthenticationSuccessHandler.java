package com.coedelsur;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
 
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        boolean doctor = false;
        logger.info("AT onAuthenticationSuccess(...) !");
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_DOCTOR".equals(auth.getAuthority())){
              doctor = true;
            }
        }
        if(doctor){
          response.sendRedirect("/doctor/inicio.xhtml");
        }else{
          response.sendRedirect("/paciente/inicioPaciente.xhtml");
        }
  }
}