package com.coedelsur.service;

import com.coedelsur.model.Usuario;
import com.coedelsur.prueba.PasswordResetToken;

public interface UserServiceServ {

    public String createPasswordResetTokenForUser(Usuario user, String token) throws Exception;
    
    public PasswordResetToken getPasswordResetToken(String token)throws Exception;
    
    void changeUserPassword(Usuario user, String password)throws Exception;


}
