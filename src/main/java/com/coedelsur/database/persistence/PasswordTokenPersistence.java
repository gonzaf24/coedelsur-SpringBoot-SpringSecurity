package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.coedelsur.database.connections.ManagerDB;
import com.coedelsur.database.connections.Querys;
import com.coedelsur.model.Usuario;
import com.coedelsur.prueba.PasswordResetToken;

public class PasswordTokenPersistence extends UtilPersistence {

	public static long createPasswordResetTokenForUser(PasswordResetToken myToken) throws Exception {
		
		long salida = 0;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_INSERT_MYTOKEN, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, convertFromJAVADateToSQLDate(myToken.getExpiryDate()));
            ps.setString(2, myToken.getToken());
            ps.setInt(3, myToken.getUser().getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                salida = rs.getLong(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                throw e;
            }
        }
        return salida;
	}

	public static PasswordResetToken getPasswordResetToken(String token) throws Exception {
		Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PasswordResetToken salida = new PasswordResetToken();
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_OBT_TOKEN);
            ps.setString(1, token);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Timestamp date = rs.getTimestamp("expirydate");
                String tokenAux = rs.getString("token");
                Integer user_id = rs.getInt("user_id");
                salida.setExpiryDate(date);
                salida.setToken(tokenAux);
                Usuario u = new Usuario();
                u.setId(user_id);
                salida.setUser(u);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                throw e;
            }
        }
        return salida;
	}

	public static void changeUserPassword(Usuario user, String password) throws Exception {
		Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_UPDATE_PSSWRD);
            ps.setString(1, password);
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                throw e;
            }
        }
		
	}
}
