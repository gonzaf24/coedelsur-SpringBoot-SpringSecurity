package com.coedelsur.form;

import java.io.Serializable;

public class RecuperarPasswordForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;

    public RecuperarPasswordForm() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
