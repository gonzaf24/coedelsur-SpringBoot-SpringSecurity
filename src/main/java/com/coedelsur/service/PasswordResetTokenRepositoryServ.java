package com.coedelsur.service;

import java.util.Date;
import java.util.stream.Stream;

import com.coedelsur.model.Usuario;
import com.coedelsur.prueba.PasswordResetToken;

public interface PasswordResetTokenRepositoryServ {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(Usuario user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    void deleteAllExpiredSince(Date now);
}
