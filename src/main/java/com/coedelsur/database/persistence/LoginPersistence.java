package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.coedelsur.database.connections.DBManager;
import com.coedelsur.model.Usuario;

public class LoginPersistence extends UtilPersistence{

    private static Logger logger = Logger.getLogger(LoginPersistence.class);

    private static Usuario login(String userName, String password) {
        logger.debug(userName + password);
        Connection conexion = null;
        Usuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_USUARIO);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String user = rs.getString("usuario");
                String pass = rs.getString("pass");
                String role = rs.getString("role");
                String tipo = rs.getString("tipo");
                boolean habilitado = rs.getBoolean("habilitado");
                usuario = new Usuario(id, user, pass, role,tipo, habilitado);
            }
        } catch (Exception e) {
            logger.error(userName + password + e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(userName+password+e);
            }
        }
        return usuario;
    }

    public static Usuario validarObtenerUsuario(String userName, String password) {
        Usuario usu = null;
        if (userName != null && password != null) {
            usu = login(userName, password);
        }
        return usu;
    }

    public static boolean validarUsuario(String userName, String password) {
        boolean validado = false;
        if (userName != null && password != null) {
            validado = login(userName, password) != null;
        }
        return validado;
    }

    public static Usuario obtenerUsuario(String userName) {
        logger.debug(userName);
        Connection conexion = null;
        Usuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_USUARIO);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("id");
                String user = rs.getString("usuario");
                String pass = rs.getString("pass");
                String role = rs.getString("role");
                String tipo = rs.getString("tipo");
                boolean habilitado = rs.getBoolean("habilitado");
                usuario = new Usuario(id, user, pass, role,tipo, habilitado);
            }
        } catch (Exception e) {
            logger.error(userName, e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(userName, e);
            }
        }
        return usuario;
    }
    
}
