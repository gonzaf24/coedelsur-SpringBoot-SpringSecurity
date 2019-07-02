package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.coedelsur.database.connections.ManagerDB;
import com.coedelsur.database.connections.Querys;
import com.coedelsur.model.Archivo;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;
import com.coedelsur.model.SelectStringValue;

public class PacientePersistence extends UtilPersistence {

    public static Paciente obtenerPaciente(Integer idPaciente) throws Exception {
        Paciente salida = new Paciente();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_PACIENTE_BY_ID);
            ps.setInt(1, idPaciente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idD");
                Integer ci = rs.getInt("ciD");
                String nombre = rs.getString("nombreD");
                String apellidos = rs.getString("apellidosD");
                String telefono = rs.getString("telefonoD");
                String domicilio = rs.getString("domicilioD");
                Date fecha_nacimiento = rs.getDate("fechaNaciemientoD");
                Integer idDepartamento = rs.getInt("idDepartamentoD");
                String nombreDep = rs.getString("nombreDep");
                String email = rs.getString("email");
                String foto = rs.getString("fotoD");
                salida = new Paciente(id, ci, nombre, apellidos, telefono, idDepartamento, domicilio, fecha_nacimiento, email, foto);
                salida.setDepartamento(new SelectStringValue(nombreDep, idDepartamento));
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

    public static boolean registrarPaciente(Paciente paciente) throws Exception {
        boolean salida = false;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_INSERT_PACIENTE);
            ps.setInt(1, paciente.getId());
            ps.setInt(2, paciente.getCedulaIdentidad());
            ps.setString(3, paciente.getNombre());
            ps.setString(4, paciente.getApellidos());
            ps.setString(5, paciente.getTelefono());
            ps.setInt(6, paciente.getIdDepartamento());
            ps.setString(7, paciente.getDomicilio());
            ps.setDate(8, convertFromJAVADateToSQLDate(paciente.getFechaNacimiento()));
            ps.setDate(9, convertFromJAVADateToSQLDate(new java.util.Date()));
            rs = ps.executeQuery();
            while (rs.next()) {
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

    public static ArrayList<PacienteFile> obtenerArchivosPaciente(Integer idPaciente) throws Exception {
        ArrayList<PacienteFile> salida = new ArrayList<PacienteFile>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_FILES_PACIENTE);
            ps.setInt(1, idPaciente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer idDoctor = rs.getInt("id_doctor");
                Date fechaCreacion = rs.getDate("fecha_creacion");
                String descripcion = rs.getString("descripcion");
                String tipoFile = rs.getString("tipo_archivo");
                salida.add(new PacienteFile(id, idPaciente, idDoctor, descripcion, fechaCreacion, tipoFile));
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

    public static Boolean nuevoArchivoPaciente(PacienteFile pacienteFile) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_INSERT_FILES_PACIENTE);
            ps.setInt(1, pacienteFile.getIdPaciente());
            ps.setInt(2, pacienteFile.getIdDoctor());
            ps.setString(3, pacienteFile.getDescripcion());
            ps.setString(4, pacienteFile.getArchivo().getDocumento());
            ps.setString(5, pacienteFile.getTipoFile());
            ps.setDate(6, convertFromJAVADateToSQLDate(new java.util.Date()));
            ps.setBoolean(7, Boolean.TRUE);
            ps.execute();
            return true;
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

    public static Archivo obtenerArchivo(Integer idArchivo) throws Exception {
        Archivo salida = new Archivo();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_FILE_PACIENTE);
            ps.setInt(1, idArchivo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String tipoFile = rs.getString("tipo_archivo");
                String archivo = rs.getString("archivo");
                String descripcion = rs.getString("descripcion");
                salida = new Archivo(id, archivo, descripcion, tipoFile);
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

    public static ArrayList<Paciente> obtenerPacientesFiltro(String nombreIN, String apellidosIN, Integer ciIN) throws Exception {
        ArrayList<Paciente> salida = new ArrayList<Paciente>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            String queryStr = "SELECT * FROM pacientes";
            ps = conexion.prepareStatement(queryStr);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer ci = rs.getInt("ci");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                Paciente p = new Paciente();
                p.setId(id);
                p.setCedulaIdentidad(ci);
                p.setNombre(nombre);
                p.setApellidos(apellidos);
                p.setFechaNacimiento(fechaNacimiento);
                salida.add(p);
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

    public static boolean eliminarDocumento(PacienteFile pacienteFile) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_ELIMINAR_FILE);
            ps.setInt(1, pacienteFile.getId());
            int rows = ps.executeUpdate();
            if (rows == 1) {
                return true;//ok
            } else {
                return false;//error
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
    }
    
    
    public static boolean editarPaciente(Paciente pacienteSelectedEdicion) throws Exception {
        
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_EDITAR_PACIENTE);
            ps.setInt(1, pacienteSelectedEdicion.getCedulaIdentidad());
            ps.setString(2, pacienteSelectedEdicion.getNombre());
            ps.setString(3, pacienteSelectedEdicion.getApellidos());
            ps.setString(4, pacienteSelectedEdicion.getTelefono());
            ps.setString(5, pacienteSelectedEdicion.getDomicilio());
            ps.setInt(6, pacienteSelectedEdicion.getDepartamento().getCode());
            ps.setDate(7, convertFromJAVADateToSQLDate(pacienteSelectedEdicion.getFechaNacimiento()));
            ps.setString(8, pacienteSelectedEdicion.getFoto());
            ps.setInt(9, pacienteSelectedEdicion.getId());
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
