package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.coedelsur.database.connections.ManagerDB;
import com.coedelsur.database.connections.Querys;
import com.coedelsur.model.Usuario;

public class UsuarioPersistence extends UtilPersistence {

    public static void cambiarPassword(String psw, Integer id) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_UPDATE_PSSWRD);
            ps.setString(1, psw);
            ps.setInt(2, id);
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

    public static String obtenerPassword(Integer id) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String salida = new String();
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_OBT_PSSWRD);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                salida = rs.getString("pass");
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

    public static Usuario obtenerUsuario(Integer id) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_OBT_USUARIO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String user = rs.getString("usuario");
                String pass = rs.getString("pass");
                String role = rs.getString("role");
                String tipo = rs.getString("tipo");
                boolean habilitado = rs.getBoolean("habilitado");
                usuario = new Usuario(id, user, pass, role, tipo, habilitado);
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
        return usuario;
    }

    public static long registrarUsuario(Usuario usuario) throws Exception {
        long salida = 0;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_INSERT_USUARIO, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getTipo());
            ps.setString(4, usuario.getRole());
            ps.setBoolean(5, Boolean.FALSE);
            ps.setDate(6, convertFromJAVADateToSQLDate(new java.util.Date()));
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

    public static boolean existeEmail(String email) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_EXISTE_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                //String user = rs.getString("usuario");
                return false;
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
        return true;
    }

    public static void registrarLogueo(String string) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_INSERT_LOGUEO_USUARIO);
            ps.setString(1, string);
            ps.setTimestamp(2, convertFromJAVADateToSQLTimeStamp(new java.util.Date()));
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
    
    
    public static ArrayList<String> obtenerRegistroLogueo() throws Exception {
        ArrayList<String>  salida = new ArrayList<String>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_SELECT_LOGUEO_USUARIO);
            rs = ps.executeQuery();
            while (rs.next()) {
                String user = rs.getString("usuario");
                Timestamp date = rs.getTimestamp("fecha");
                String salida1 = new String( user + " - " + date );
                salida.add(salida1);
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
}
