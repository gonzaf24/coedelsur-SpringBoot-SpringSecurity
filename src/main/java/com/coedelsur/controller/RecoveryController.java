package com.coedelsur.controller;

import java.util.Calendar;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coedelsur.database.persistence.LoginPersistence;
import com.coedelsur.model.Usuario;
import com.coedelsur.prueba.PasswordResetToken;
import com.coedelsur.service.UserServiceServ;

@Controller
@RequestMapping(value = "/old")
public class RecoveryController {

    @Inject
    private UserServiceServ userService;

   
    public RecoveryController() {
        super();
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String changePassword(final HttpServletRequest request, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) throws Exception {

        final PasswordResetToken passToken = userService.getPasswordResetToken(token);
        final Usuario user = passToken.getUser();
        if ((passToken == null) || (user.getId() != id)) {
            FacesContext.getCurrentInstance().addMessage("messagesUpdate", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo incorrecto.", ""));
            return "redirect:/login.xhtml";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            FacesContext.getCurrentInstance().addMessage("messagesUpdate", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Su ficha de registro ha caducado, por favor registrese de nuevo..", ""));
            return "redirect:/login.xhtml";
        }

        return "redirect:/updatePassword.xhtml?idPers="+id;
    }
    
    public static UserDetails loadUserByUsername(String userName) {
		Usuario user = LoginPersistence.obtenerUsuario(userName);
		if (user != null && user.isEnabled()) {
			return user;
		}
		return null;
	}


}
