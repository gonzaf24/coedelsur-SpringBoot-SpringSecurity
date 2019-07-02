package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.coedelsur.database.connections.DBManager;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.SelectStringValue;

public class ConsultaPersistence extends UtilPersistence {

    public static ArrayList<ConsultaDoctor> obtenerConsultasHabilitados() throws Exception {
        ArrayList<ConsultaDoctor> salida = new ArrayList<ConsultaDoctor>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_CONSULTAS_HABILITADOS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idCon");
                String descripcion = rs.getString("descripcionCon");
                Integer precio = rs.getInt("precioCon");
                Integer idDoctor = rs.getInt("idDoctorCon");
                Integer idTipoConsulta = rs.getInt("idTipoConsultaCon");
                Integer idEspecialidad = rs.getInt("idEspecialidadCon");
                Boolean habilitado = rs.getBoolean("habilitadoCon");
                String descripcionTC = rs.getString("descripcionTC");
                String descripcionESP = rs.getString("nombreEsp");
                salida.add(new ConsultaDoctor(id, descripcion, precio, idDoctor, new SelectStringValue(descripcionTC,idTipoConsulta), new SelectStringValue(descripcionESP,idEspecialidad), habilitado));
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
    
    public static ArrayList<ConsultaDoctor> obtenerConsultas() throws Exception {
        ArrayList<ConsultaDoctor> salida = new ArrayList<ConsultaDoctor>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_CONSULTAS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idCon");
                String descripcion = rs.getString("descripcionCon");
                Integer precio = rs.getInt("precioCon");
                Integer idDoctor = rs.getInt("idDoctorCon");
                Integer idTipoConsulta = rs.getInt("idTipoConsultaCon");
                Integer idEspecialidad = rs.getInt("idEspecialidadCon");
                Boolean habilitado = rs.getBoolean("habilitadoCon");
                String descripcionTC = rs.getString("descripcionTC");
                String descripcionESP = rs.getString("nombreEsp");
                salida.add(new ConsultaDoctor(id, descripcion, precio, idDoctor, new SelectStringValue(descripcionTC,idTipoConsulta), new SelectStringValue(descripcionESP,idEspecialidad), habilitado));
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

    public static boolean crearConsulta(ConsultaDoctor consDoc) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_CONSULTA);
            ps.setString(1, consDoc.getDescripcion());
            ps.setInt(2, consDoc.getPrecio());
            ps.setInt(3, consDoc.getTipoConsulta().getCode());
            ps.setInt(4, consDoc.getEspecialidad().getCode());
            ps.setBoolean(5, Boolean.TRUE);
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
        return true;
    }

    public static boolean crearCodigoTipoConsulta(String descripcion) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_TIPO_CONSULTA);
            ps.setString(1, descripcion);
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
        return true;
    }

    public static boolean crearCodigoEspecialidad(String descripcion) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_ESPECIALIDAD);
            ps.setString(1, descripcion);
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
        return true;
    }

    public static boolean crearCodigoConsultorio(String descripcion) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_CONSULTORIO);
            ps.setString(1, descripcion);
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
        return true;
    }
    
    
   
}
