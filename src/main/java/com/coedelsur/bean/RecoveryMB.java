package com.coedelsur.bean;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.coedelsur.database.persistence.LoginPersistence;
import com.coedelsur.model.Mail;
import com.coedelsur.model.Usuario;
import com.coedelsur.service.UserServiceServ;
import com.coedelsur.service.impl.EmailServiceImpl;

@Named
@ApplicationScoped
public class RecoveryMB implements Serializable {

	@Inject
	private UserServiceServ userService;
	@Inject
    private EmailServiceImpl emailService;
	@Inject
    private ApplicationContext applicationContext;

	private static final long serialVersionUID = 1L;
	private String email;
	private String nuewPass1;
	private String nuewPass2;

	public RecoveryMB() {
		super();
	}

	public void resetPassword() {
		
		Usuario user = LoginPersistence.obtenerUsuario(email);
		
		if (user == null) {
            FacesContext.getCurrentInstance().addMessage("messagesRecuperar", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no existe.", ""));
		}
		
		final String token = UUID.randomUUID().toString();
		
		try {
			userService.createPasswordResetTokenForUser(user, token);
			String serverName = InetAddress.getLocalHost().getHostAddress();
	        Integer port = applicationContext.getBean(Environment.class).getProperty("server.port", Integer.class, 8080);
			String appUrl = "http://"+serverName+":"+port;
			final String url = appUrl+"/old/user/changePassword?id="+user.getId()+"&token="+token;
			Mail mail = new Mail("gonzaf21@hotmail.com", user.getUsername(), "Resetear Password COEdelSUR", " Clica en el siguiente enlace para restaurar su constraseña \r\n" + url);
			emailService.sendSimpleMessage(mail);
		} catch (final MailAuthenticationException e) {
            FacesContext.getCurrentInstance().addMessage("messagesRecuperar", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar email.", ""));
		} catch (final Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesRecuperar", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar email.", ""));

		}
		
        FacesContext.getCurrentInstance().addMessage("messagesRecuperar", new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulte su casilla de correo con el email y el link.", ""));

	}
	
    public void savePassword() throws Exception {
    	try {
	    	final Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        userService.changeUserPassword(user, nuewPass1);
	        FacesContext.getCurrentInstance().addMessage("messagesLogin", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cantraseña cambiada con éxito.", ""));
    	} catch (final MailAuthenticationException e) {
            FacesContext.getCurrentInstance().addMessage("messagesUpdate", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar email.", ""));
    	}
    }

	public String getNuewPass1() {
		return nuewPass1;
	}

	public void setNuewPass1(String nuewPass1) {
		this.nuewPass1 = nuewPass1;
	}

	public String getNuewPass2() {
		return nuewPass2;
	}

	public void setNuewPass2(String nuewPass2) {
		this.nuewPass2 = nuewPass2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
