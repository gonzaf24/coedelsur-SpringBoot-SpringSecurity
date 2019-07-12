package com.coedelsur.service.impl;

import java.util.Date;
import java.util.stream.Stream;

import com.coedelsur.model.Usuario;
import com.coedelsur.prueba.PasswordResetToken;
import com.coedelsur.service.PasswordResetTokenRepositoryServ;

public class PasswordResetTokenRepositoryServImpl implements PasswordResetTokenRepositoryServ{

	@Override
	public PasswordResetToken findByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PasswordResetToken findByUser(Usuario user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByExpiryDateLessThan(Date now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllExpiredSince(Date now) {
		// TODO Auto-generated method stub
		
	}

   
}
