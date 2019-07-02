package com.coedelsur;

import org.springframework.security.core.userdetails.UserDetails;

import com.coedelsur.database.persistence.LoginPersistence;
import com.coedelsur.model.Usuario;

public class UserDetailService {

	public static UserDetails loadUserByUsername(String userName) {
		Usuario user = LoginPersistence.obtenerUsuario(userName);
		if (user != null && user.isEnabled()) {
			return user;
		}
		return null;
	}

}