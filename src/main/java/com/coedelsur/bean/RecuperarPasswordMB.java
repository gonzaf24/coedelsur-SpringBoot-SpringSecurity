package com.coedelsur.bean;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class RecuperarPasswordMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;

    public RecuperarPasswordMB() {
        super();
    }
    
    public void recuperarPassword() {
    	//aqui implementar la llamada a recuperar el password
        this.getEmail();
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
