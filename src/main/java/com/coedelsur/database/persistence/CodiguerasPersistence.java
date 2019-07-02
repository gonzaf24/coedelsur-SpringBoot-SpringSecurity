package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.coedelsur.database.connections.DBManager;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringString;
import com.coedelsur.model.SelectStringValue;


public class CodiguerasPersistence extends UtilPersistence {

    private static Logger logger = Logger.getLogger(CodiguerasPersistence.class);

    public static ArrayList<SelectStringValue> obtenerCodigueraDepartamentosList() {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SelectStringValue obj;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_DEPARTAMENTOS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                obj = new SelectStringValue(descripcion, id);
                salida.add(obj);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }

    public static ArrayList<SelectStringValue> obtenerTipoConsultas() {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SelectStringValue tc;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_TIPO_CONSULTAS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                boolean habilitado = rs.getBoolean("habilitado");
                tc = new SelectStringValue(descripcion ,id,  habilitado);
                salida.add(tc);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }
    
    public static ArrayList<SelectStringValue> obtenerTipoConsultasTodas() {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SelectStringValue tc;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_TIPO_CONSULTAS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                boolean habilitado = rs.getBoolean("habilitado");
                tc = new SelectStringValue(descripcion ,id,  habilitado);
                salida.add(tc);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }

    public static ArrayList<SelectStringValue> obtenerEspecialidades() {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SelectStringValue espe;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_ESPECIALIDADES);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                boolean habilitado = rs.getBoolean("habilitado");
                espe = new SelectStringValue(nombre,id, habilitado);
                salida.add(espe);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }
    
    public static ArrayList<SelectStringValue> obtenerEspecialidadesTodas() {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SelectStringValue espe;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_ESPECIALIDADES);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                boolean habilitado = rs.getBoolean("habilitado");
                espe = new SelectStringValue(nombre,id, habilitado);
                salida.add(espe);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }

    public static ArrayList<Doctor> obtenerDoctoresPorEspecialidad(Integer idEspecialidad) {
        ArrayList<Doctor> salida = new ArrayList<Doctor>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_DOCTORES);
            ps.setInt(1, idEspecialidad);
            rs = ps.executeQuery();
            Doctor doc;
            while (rs.next()) {
                
                Integer id = rs.getInt("idDoc");
                String nombre = rs.getString("nombreDoc");
                String apellidos = rs.getString("apellidosDoc");
                Integer ci = rs.getInt("ciDoc");
                String sexo = rs.getString("sexoDoc");
                String nomEspecialidad = rs.getString("nombreEsp");
                String foto = rs.getString("fotoDoc");
                String sexoLabel="";
                if(sexo.equals("H")){
                    sexoLabel= "Dr.";
                }else{
                    sexoLabel="Dra.";
                }
                doc = new Doctor();
                SelectStringValue especialidad = new SelectStringValue(nomEspecialidad,idEspecialidad);
                doc.setId(id);
                doc.setCedulaIdentidad(ci);
                doc.setNombre(nombre);
                doc.setApellidos(apellidos);
                doc.setSexo(new SelectStringString(sexoLabel,sexo));
                doc.setEspecialidad(especialidad);
                doc.setFoto(foto);
                salida.add(doc);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }

    public static ArrayList<SelectStringValue> obtenerEspecialidadesPorTipoConsultaSolicitarCita(Integer id) {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SelectStringValue espe;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_ESPECIALIDADES_POR_TIPO_CONSULTA_SOLICITAR_CITA);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer idEsp = rs.getInt("idEsp");
                String nombre = rs.getString("nombre");
                boolean habilitado = rs.getBoolean("habilitado");
                espe = new SelectStringValue(nombre,idEsp, habilitado);
                salida.add(espe);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }
    
    public static ArrayList<SelectStringValue> obtenerTipoConsultasSolicitarCita() throws Exception {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SelectStringValue tc;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_TIPO_CONSULTAS_SOLICITAR_CITA);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("idTipoCons");
                String descripcion = rs.getString("descTipoCons");
                boolean habilitado = rs.getBoolean("habilitadoTipoCons");
                tc = new SelectStringValue(descripcion ,id,  habilitado);
                salida.add(tc);
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
    
    public static ArrayList<SelectStringValue> obtenerConsultoriosTodos() {
        ArrayList<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SelectStringValue consultorio;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_CONSULTORIOS_TODOS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                boolean activo = rs.getBoolean("activo");
                consultorio = new SelectStringValue(descripcion, id, activo);
                salida.add(consultorio);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }


//    @SuppressWarnings("unchecked")
//    private static ArrayList<SelectStringValue> obtenerCodigueraDepartamentosListSession() {
//        return (ArrayList<SelectStringValue>) sessionState.getParameter(ConstantesUY.DEPARTAMENTOS_LIST);
//    }

    public static ConsultaDoctor obtenerConsultaSolicitarCita(Integer codTipoConsulta, Integer codEspecialidad) {
        ConsultaDoctor salida = new ConsultaDoctor();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_CONSULTA_SOLICITAR_CITA);
            ps.setInt(1, codTipoConsulta);
            ps.setInt(2, codEspecialidad);
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
                
                salida = new ConsultaDoctor(id, descripcion, precio, idDoctor, new SelectStringValue(descripcionTC,idTipoConsulta), new SelectStringValue(descripcionESP,idEspecialidad), habilitado);
                
            }
            
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return salida;
    }

    

}
