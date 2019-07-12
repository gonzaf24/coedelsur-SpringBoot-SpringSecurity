package com.coedelsur.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.coedelsur.database.persistence.PasswordTokenPersistence;
import com.coedelsur.model.Usuario;
import com.coedelsur.prueba.PasswordResetToken;
import com.coedelsur.service.UserServiceServ;

@Service
@Transactional
public class UserServiceImpl implements UserServiceServ {

	public static final String TOKEN_INVALID = "invalidToken";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String TOKEN_VALID = "valid";

	public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
	public static String APP_NAME = "SpringRegistration";

	@Override
	public String createPasswordResetTokenForUser(Usuario user, String token) throws Exception {
		try {
			final PasswordResetToken myToken = new PasswordResetToken(token, user);
			PasswordTokenPersistence.createPasswordResetTokenForUser(myToken);
			return "OK";
		} catch (Exception e) {
			throw new Exception("Hubo un problema token.");
		}
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) throws Exception {
		return PasswordTokenPersistence.getPasswordResetToken(token);
	}

	@Override
	public void changeUserPassword(Usuario user, String password) throws Exception {
		try {
			PasswordTokenPersistence.changeUserPassword(user,password);
		} catch (Exception e) {
			throw new Exception("Hubo un problema al cambiar password.");
		}
	}

}
