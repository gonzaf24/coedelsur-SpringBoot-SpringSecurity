package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.coedelsur.database.connections.DBManager;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringString;
import com.coedelsur.model.SelectStringValue;

public class DoctorPersistence extends UtilPersistence {

    public static Doctor obtenerDoctor(Integer idDoctor) throws Exception {
        Doctor salida = new Doctor();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_DOCTOR_BY_ID);
            ps.setInt(1, idDoctor);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idD");
                Integer ci = rs.getInt("ciD");
                String nombre = rs.getString("nombreD");
                String apellidos = rs.getString("apellidosD");
                String telefono = rs.getString("telefonoD");
                String domicilio = rs.getString("domicilioD");
                Integer idDepartamento = rs.getInt("idDepartamentoD");
                Integer idEspecialidad = rs.getInt("idEspecialidadD");
                Boolean activo = rs.getBoolean("activoD");
                String sexo = rs.getString("sexoD");
                String nombreEsp = rs.getString("nombreE");
                String nombreDep = rs.getString("nombreDep");
                String fotoD = rs.getString("fotoD");
                String sexoLabel = "";
                if (sexo.equals("H")) {
                    sexoLabel = "Dr.";
                } else {
                    sexoLabel = "Dra.";
                }
                salida = new Doctor(id, ci, nombre, apellidos, new SelectStringString(sexoLabel,sexo), telefono, domicilio, new SelectStringValue(nombreDep,idDepartamento), new SelectStringValue(nombreEsp, idEspecialidad),activo,fotoD );
                return salida;
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

    public static void registrarDoctor(Doctor doctor) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_DOCTOR);
            ps.setInt(1, doctor.getId());
            ps.setInt(2, doctor.getCedulaIdentidad());
            ps.setInt(3, doctor.getEspecialidad().getCode());
            ps.setString(4, doctor.getNombre());
            ps.setString(5, doctor.getApellidos());
            ps.setString(6, doctor.getTelefono());
            ps.setString(7, doctor.getDomicilio());
            ps.setInt(8, doctor.getDepartamento().getCode());
            ps.setDate(9, convertFromJAVADateToSQLDate(new java.util.Date()));
            ps.setBoolean(10, doctor.getActivo());
            ps.setString(11, doctor.getSexo().getCode());
            ps.setString(12, doctor.getFoto());
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

    public static ArrayList<Doctor> obtenerListaDoctores() throws Exception {
        ArrayList<Doctor> salida = new ArrayList<Doctor>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_LIST_DOCTORES);
            rs = ps.executeQuery();
            Doctor doc;
            while (rs.next()) {
                
                Integer id = rs.getInt("idD");
                Integer ci = rs.getInt("ciD");
                String nombre = rs.getString("nombreD");
                String apellidos = rs.getString("apellidosD");
                String telefono = rs.getString("telefonoD");
                String domicilio = rs.getString("domicilioD");
                Integer idDepartamento = rs.getInt("idDepartamentoD");
                Integer idEspecialidad = rs.getInt("idEspecialidadD");
                Boolean activo = rs.getBoolean("activoD");
                String sexo = rs.getString("sexoD");
                String foto = rs.getString("fotoD");
                String nombreEsp = rs.getString("nombreE");
                String nombreDep = rs.getString("nombreDep");
                String sexoLabel = "";
                if (sexo.equals("H")) {
                    sexoLabel = "Dr.";
                } else {
                    sexoLabel = "Dra.";
                }
                doc = new Doctor(id, ci, nombre, apellidos, new SelectStringString(sexoLabel,sexo), telefono, domicilio, new SelectStringValue(nombreDep,idDepartamento), new SelectStringValue(nombreEsp, idEspecialidad), activo);
                doc.setFoto(foto);
                salida.add(doc);
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

    public static boolean editarDoctor(Doctor doctorSelectedEdicion) throws Exception {
        
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_EDITAR_DOCTOR);
            ps.setInt(1, doctorSelectedEdicion.getCedulaIdentidad());
            ps.setString(2, doctorSelectedEdicion.getNombre());
            ps.setString(3, doctorSelectedEdicion.getApellidos());
            ps.setString(4, doctorSelectedEdicion.getTelefono());
            ps.setString(5, doctorSelectedEdicion.getDomicilio());
            ps.setInt(6, doctorSelectedEdicion.getEspecialidad().getCode());
            ps.setInt(7, doctorSelectedEdicion.getDepartamento().getCode());
            ps.setString(8, doctorSelectedEdicion.getSexo().getCode());
            ps.setString(9, doctorSelectedEdicion.getFoto());
            ps.setInt(10, doctorSelectedEdicion.getId());
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

    public static ArrayList<SelectStringValue> obtenerListaDoctoresCodigos() throws Exception {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_LIST_DOCTORES_CODIGOS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idD");
                String nombre = rs.getString("nombreD");
                String apellidos = rs.getString("apellidosD");
                String sexo = rs.getString("sexoD");
                String sexoLabel = "";
                if (sexo.equals("H")) {
                    sexoLabel = "Dr.";
                } else {
                    sexoLabel = "Dra.";
                }
                String nombreCompleto = sexoLabel + " " + nombre + " " + apellidos;
                SelectStringValue aux = new SelectStringValue(nombreCompleto.toString(),id);
                salida.add(aux);
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
