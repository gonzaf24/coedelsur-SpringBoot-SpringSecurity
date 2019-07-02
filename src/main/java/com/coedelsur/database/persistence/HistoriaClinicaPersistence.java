package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.coedelsur.database.connections.DBManager;
import com.coedelsur.model.Antecedente;
import com.coedelsur.model.AntecedenteObject;
import com.coedelsur.model.ConsultaMedica;
import com.coedelsur.model.HistoriaClinica;
import com.coedelsur.model.Medicamento;
import com.coedelsur.model.Paciente;

public class HistoriaClinicaPersistence extends UtilPersistence {

    public static ArrayList<HistoriaClinica> obtenerHistoriaClinica(Integer idPaciente) throws Exception {
        ArrayList<HistoriaClinica> salida = new ArrayList<HistoriaClinica>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_HISTORIA_CLINICA_BY_ID_PACIENTE);
            ps.setInt(1, idPaciente);
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

    public static boolean crearHistoriaClinica(Paciente paciente) throws Exception {
        boolean salida = false;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_HISTORIA_CLINICA);
            ps.setInt(1, paciente.getId());
            ps.setInt(2, paciente.getCedulaIdentidad());
            ps.setInt(3, 0);
            ps.setDate(4, convertFromJAVADateToSQLDate(new java.util.Date()));
            ps.setBoolean(5, Boolean.TRUE);
            ps.execute();
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
    
    public static boolean nuevaConsultaMedica(ConsultaMedica consulta) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long generado = 0;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_CONSULTA_MEDICA ,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, consulta.getIdPaciente());
            ps.setInt(2, consulta.getIdDoctor());
            ps.setDate(3, convertFromJAVADateToSQLDate(new java.util.Date()));
            ps.setBoolean(4, consulta.getRepetirMedicamento());
            ps.setString(5, consulta.getMotivoConsulta());
            ps.setString(6, consulta.getAnemesis());
            ps.setString(7, consulta.getExamenFisico());
            ps.setInt(8, consulta.getPesoKgs());
            ps.setInt(9, consulta.getTallaCms());
            ps.setInt(10, consulta.getImc());
            ps.setInt(11, consulta.getCinturaCms());
            ps.setString(12, consulta.getDiagnostico());
            ps.setString(13, consulta.getConducta());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                generado = rs.getLong(1);
                Long idGenerado = new Long(generado); 
                closeCon(conexion, ps, rs);
                conexion = DBManager.getDBConection();
                ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_DIAGNOSTICO_DE_CONSULTA_MEDICA );
                ps.setInt(1, consulta.getDiagnosticoObject().getIdDoctor());
                ps.setInt(2, consulta.getDiagnosticoObject().getIdPaciente());
                ps.setInt(3, idGenerado.intValue());
                ps.setDate(4, convertFromJAVADateToSQLDate(new java.util.Date()));
                ps.setString(5, consulta.getDiagnosticoObject().getTipo());
                ps.setString(6, consulta.getDiagnosticoObject().getDescripcion());
                ps.setString(7, consulta.getDiagnosticoObject().getId10());
                ps.setString(8, consulta.getDiagnosticoObject().getDec10());
                ps.setString(9, consulta.getDiagnosticoObject().getGrp10());
                ps.executeUpdate();
                closeCon(conexion, ps, rs);

            }else{
                closeCon(conexion, ps, rs);
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
    
    public static boolean editarDiagnosticoDeConsultaMedica(ConsultaMedica consulta) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_EDITAR_DIAGNOSTICO_DE_CONSULTA_MEDICA);
            ps.setInt(1, consulta.getDiagnosticoObject().getIdDoctor());
            ps.setInt(2, consulta.getDiagnosticoObject().getIdPaciente());
            ps.setString(3, consulta.getDiagnosticoObject().getTipo());
            ps.setString(4, consulta.getDiagnosticoObject().getDescripcion());
            ps.setString(5, consulta.getDiagnosticoObject().getId10());
            ps.setString(6, consulta.getDiagnosticoObject().getDec10());
            ps.setString(7, consulta.getDiagnosticoObject().getGrp10());
            ps.setInt(8, consulta.getDiagnosticoObject().getIdConsulta());
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
    
    public static boolean editarConsultaMedica(ConsultaMedica consulta) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_EDITAR_CONSULTA_MEDICA);
            ps.setBoolean(1, consulta.getRepetirMedicamento());
            ps.setString(2, consulta.getMotivoConsulta());
            ps.setString(3, consulta.getAnemesis());
            ps.setString(4, consulta.getExamenFisico());
            ps.setInt(5, consulta.getPesoKgs());
            ps.setInt(6, consulta.getTallaCms());
            ps.setInt(7, consulta.getImc());
            ps.setInt(8, consulta.getCinturaCms());
            ps.setString(9, consulta.getDiagnosticoObject().getLabel());
            ps.setString(10, consulta.getConducta());
            ps.setInt(11, consulta.getId());
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
    
    public static boolean eliminarConsultaMedica(ConsultaMedica consulta) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_ELIMINAR_DIAGNOSTICO_DE_CONSULTA_MEDICA);
            ps.setInt(1, consulta.getId());
            int rows = ps.executeUpdate();
            closeCon(conexion, ps, rs);
            if (rows == 1) {
                conexion = DBManager.getDBConection();
                ps = conexion.prepareStatement(DBManager.CLI_QUERY_ELIMINAR_CONSULTA_MEDICA);
                ps.setInt(1, consulta.getId());
                int rows1 = ps.executeUpdate();
                if (rows1 == 1) {
                    return true;//ok
                } else {
                    return false;//error
                }
            } else {
                return false;//error
            }
          
        } catch (Exception e) {
            closeCon(conexion, ps, rs);
            throw e;
        } finally {
            try {
                closeCon(conexion, ps, rs);
            } catch (SQLException e) {
                throw e;
            }
        }
    }
    
    public static boolean nuevoAntecedente(Antecedente antecedente) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {   
        
            if(antecedente.getPersonal().getAntecedente() != null || antecedente.getFamiliar().getAntecedente() != null || antecedente.getMedicamento().getMedicamento() != null){
                
                conexion = DBManager.getDBConection();
                ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_ANTECEDENTE, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, antecedente.getIdPaciente());
                ps.setInt(2, antecedente.getIdDoctor());
                ps.setDate(3, convertFromJAVADateToSQLDate(new java.util.Date()));
                ps.execute();
                rs = ps.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    antecedente.setId(new Long(rs.getLong(1)).intValue());
                }
                closeCon(conexion, ps, rs);
                
                if(antecedente.getPersonal().getAntecedente() != null){
                    conexion = DBManager.getDBConection();
                    ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_ANTECEDENTE_PERSONAL);
                    ps.setInt(1, antecedente.getId());
                    ps.setInt(2, antecedente.getIdDoctor());
                    ps.setDate(3, convertFromJAVADateToSQLDate(new java.util.Date()));
                    ps.setString(4, antecedente.getPersonal().getAntecedente());
                    ps.setDate(5,convertFromJAVADateToSQLDate(antecedente.getPersonal().getFechaDesde()));
                    ps.setDate(6, convertFromJAVADateToSQLDate(antecedente.getPersonal().getFechaHasta()));
                    ps.setString(7, antecedente.getPersonal().getOrigen());
                    ps.setString(8, antecedente.getPersonal().getObservaciones());
                    ps.execute();
                    closeCon(conexion, ps, rs);
                }
                
                if(antecedente.getFamiliar().getAntecedente() != null){
                    conexion = DBManager.getDBConection();
                    ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_ANTECEDENTE_FAMILIAR);
                    ps.setInt(1, antecedente.getId());
                    ps.setInt(2, antecedente.getIdDoctor());
                    ps.setDate(3, convertFromJAVADateToSQLDate(new java.util.Date()));
                    ps.setString(4, antecedente.getFamiliar().getAntecedente());
                    ps.setDate(5,convertFromJAVADateToSQLDate(antecedente.getFamiliar().getFechaDesde()));
                    ps.setDate(6, convertFromJAVADateToSQLDate(antecedente.getFamiliar().getFechaHasta()));
                    ps.setString(7, antecedente.getFamiliar().getOrigen());
                    ps.setString(8, antecedente.getFamiliar().getObservaciones());
                    ps.execute();
                    closeCon(conexion, ps, rs);
                }
                
                if(antecedente.getMedicamento().getMedicamento() != null){
                    conexion = DBManager.getDBConection();
                    ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_ANTECEDENTE_MEDICAMENTO);
                    ps.setInt(1, antecedente.getId());
                    ps.setInt(2, antecedente.getIdDoctor());
                    ps.setDate(3, convertFromJAVADateToSQLDate(new java.util.Date()));
                    ps.setString(4, antecedente.getMedicamento().getMedicamento());
                    ps.setDate(5,convertFromJAVADateToSQLDate(antecedente.getMedicamento().getFechaDesde()));
                    ps.setDate(6, convertFromJAVADateToSQLDate(antecedente.getMedicamento().getFechaHasta()));
                    ps.setString(7, antecedente.getMedicamento().getOrigen());
                    ps.setString(8, antecedente.getMedicamento().getObservaciones());
                    ps.execute();
                    closeCon(conexion, ps, rs);
                }
                
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
    
    public static boolean editarAntecedentePersonal(AntecedenteObject antecedentePersonal) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_EDITAR_ANTECEDENTE_PERSONAL);
            ps.setString(1, antecedentePersonal.getAntecedente());
            ps.setDate(2, convertFromJAVADateToSQLDate(antecedentePersonal.getFechaDesde()));
            ps.setDate(3, convertFromJAVADateToSQLDate(antecedentePersonal.getFechaHasta()));
            ps.setString(4, antecedentePersonal.getOrigen());
            ps.setString(5, antecedentePersonal.getObservaciones());
            ps.setInt(6, antecedentePersonal.getId());
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
    
    
    public static boolean eliminarAntecedentePersonal(AntecedenteObject antecedentePersonal) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_ELIMINAR_ANTECEDENTE_PERSONAL);
            ps.setInt(1, antecedentePersonal.getId());
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
    
    public static boolean editarAntecedenteFamiliar(AntecedenteObject antecedenteFamiliar) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_EDITAR_ANTECEDENTE_PERSONAL);
            ps.setString(1, antecedenteFamiliar.getAntecedente());
            ps.setDate(2, convertFromJAVADateToSQLDate(antecedenteFamiliar.getFechaDesde()));
            ps.setDate(3, convertFromJAVADateToSQLDate(antecedenteFamiliar.getFechaHasta()));
            ps.setString(4, antecedenteFamiliar.getOrigen());
            ps.setString(5, antecedenteFamiliar.getObservaciones());
            ps.setInt(6, antecedenteFamiliar.getId());
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
    
    
    public static boolean eliminarAntecedenteFamiliar(AntecedenteObject antecedenteFamiliar) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_ELIMINAR_ANTECEDENTE_FAMILIAR);
            ps.setInt(1, antecedenteFamiliar.getId());
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
    
    
    public static boolean editarAntecedenteMedicamento(Medicamento medicamento) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_EDITAR_ANTECEDENTE_MEDICAMENTO);
            ps.setString(1, medicamento.getMedicamento());
            ps.setDate(2, convertFromJAVADateToSQLDate(medicamento.getFechaDesde()));
            ps.setDate(3, convertFromJAVADateToSQLDate(medicamento.getFechaHasta()));
            ps.setString(4, medicamento.getOrigen());
            ps.setString(5, medicamento.getObservaciones());
            ps.setInt(6, medicamento.getId());
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
    
    public static boolean eliminarAntecedenteMedicamento(Medicamento medicamento) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_ELIMINAR_ANTECEDENTE_MEDICAMENTO);
            ps.setInt(1, medicamento.getId());
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
    
    public static boolean eliminarAntecedente(Antecedente antecedente) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_ELIMINAR_ANTECEDENTE);
            ps.setInt(1, antecedente.getId());
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


    public static ArrayList<Antecedente> obtenerAntecedentesList(Integer idPaciente) throws Exception{
        ArrayList<Antecedente> salida = new ArrayList<Antecedente>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Antecedente antecedente;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_SELECT_ANTECEDENTES_BY_ID_PACIENTE);
            ps.setInt(1, idPaciente);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                    antecedente = new Antecedente();
                    //Antecedente
                    Integer antId = rs.getInt("antId"); 
                    Integer antIdPaciente = rs.getInt("antIdPaciente"); 
                    Integer antIdDoctor = rs.getInt("antIdDoctor"); 
                    Date antFechaCreacion = rs.getDate("antFechaCreacion");
                    antecedente.setId(antId);
                    antecedente.setIdDoctor(antIdDoctor);
                    antecedente.setIdPaciente(antIdPaciente);
                    antecedente.setFechaCreacion(antFechaCreacion);
                
                if(rs.getString("antPerAntecedente")!= null){
                    //antecedente personal
                    Integer antPerIdDoctor = rs.getInt("antPerIdDoctor"); 
                    Date antPerFechaCreacion = rs.getDate("antPerFechaCreacion");        
                    String antPerAntecedente  = rs.getString("antPerAntecedente");
                    Date antPerFechaDesde = rs.getDate("antPerFechaDesde");
                    Date antPerFechaHasta = rs.getDate("antPerFechaHasta");
                    String antPerOrigen  = rs.getString("antPerOrigen");
                    String antPerObservaciones  = rs.getString("antPerObservaciones"); 
                    AntecedenteObject personal = new AntecedenteObject (antId,antPerIdDoctor,idPaciente,antPerFechaCreacion,antPerAntecedente,antPerFechaDesde,antPerFechaHasta,antPerOrigen,antPerObservaciones);
                    antecedente.setPersonal(personal);
                }else{
                    antecedente.setPersonal(null);
                }
                
                if(rs.getString("antFamAntecedente")!= null){
                    //antecedente familiar
                    Integer antFamIdDoctor = rs.getInt("antFamIdDoctor"); 
                    Date antFamFechaCreacion = rs.getDate("antFamFechaCreacion");
                    String antFamAntecedente  = rs.getString("antFamAntecedente");
                    Date antFamFechaDesde = rs.getDate("antFamFechaDesde");
                    Date antFamFechaHasta = rs.getDate("antFamFechaHasta");
                    String antFamOrigen  = rs.getString("antFamOrigen");
                    String antFamObservaciones  = rs.getString("antFamObservaciones");
                    AntecedenteObject familiar = new AntecedenteObject (antId,antFamIdDoctor,idPaciente,antFamFechaCreacion,antFamAntecedente,antFamFechaDesde,antFamFechaHasta,antFamOrigen,antFamObservaciones);
                    antecedente.setFamiliar(familiar);
                }else{
                    antecedente.setFamiliar(null);
                }
                
                if(rs.getString("medicMedicamento")!= null){
                    // antecedente medicamento
                    Integer medicIdDoctor = rs.getInt("medicIdDoctor");
                    Date medicFechaCreacion = rs.getDate("medicFechaCreacion");
                    String medicMedicamento  = rs.getString("medicMedicamento");
                    Date medicFechaDesde = rs.getDate("medicFechaDesde");
                    Date medicFechaHasta = rs.getDate("medicFechaHasta");
                    String medicOrigen  = rs.getString("medicOrigen");
                    String medicObservaciones  = rs.getString("medicObservaciones");
                    Medicamento medicamento = new Medicamento (antId,medicIdDoctor,idPaciente,medicFechaCreacion,medicMedicamento,medicFechaDesde,medicFechaHasta,medicOrigen,medicObservaciones);
                    antecedente.setMedicamento(medicamento);
                }else{
                    antecedente.setMedicamento(null);
                }
                if(rs.getString("medicMedicamento")!= null||rs.getString("antFamAntecedente")!= null||rs.getString("antPerAntecedente")!= null){
                    salida.add(antecedente); 
                }
                
                
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

    public static ArrayList<ConsultaMedica> obtenerConsultasMedicasList(Integer idPaciente) throws Exception {
        ArrayList<ConsultaMedica> salida = new ArrayList<ConsultaMedica>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConsultaMedica consulta;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_CONSULTAS_MEDICAS_BY_ID_PACIENTE);
            ps.setInt(1, idPaciente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id"); 
                Integer idDoctor = rs.getInt("id_doctor"); 
                Date fecha_creacion = rs.getDate("fecha_creacion");
                Boolean repetirMedicamento = rs.getBoolean("repetir_medicamento");
                String motivo  = rs.getString("motivo");
                String anemesis  = rs.getString("anemesis");
                String examenFisico  = rs.getString("examen_fisico");
                Integer peso = rs.getInt("peso_kgs"); 
                Integer talla = rs.getInt("talla_cms"); 
                Integer imc = rs.getInt("imc"); 
                Integer cintura = rs.getInt("cintura_cms"); 
                String diagnostico  = rs.getString("diagnostico");
                String conducta  = rs.getString("conducta");
                consulta = new ConsultaMedica (id,idPaciente,idDoctor,fecha_creacion,repetirMedicamento,motivo,anemesis,examenFisico,peso,talla,imc,cintura,diagnostico,conducta);
                salida.add(consulta);
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

    
    
    public static boolean nuevoAntecedentePersonal(Antecedente antecedente) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_ANTECEDENTE_PERSONAL);
            ps.setInt(1, antecedente.getId());
            ps.setInt(2, antecedente.getIdDoctor());
            ps.setDate(3, convertFromJAVADateToSQLDate(new java.util.Date()));
            ps.setString(4, antecedente.getPersonal().getAntecedente());
            ps.setDate(5, convertFromJAVADateToSQLDate(antecedente.getPersonal().getFechaDesde()));
            ps.setDate(6, convertFromJAVADateToSQLDate(antecedente.getPersonal().getFechaHasta()));
            ps.setString(7, antecedente.getPersonal().getOrigen());
            ps.setString(8, antecedente.getPersonal().getObservaciones());
            ps.execute();
            closeCon(conexion, ps, rs);
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
    
    
    public static boolean nuevoAntecedenteFamiliar(Antecedente antecedente) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_ANTECEDENTE_FAMILIAR);
            ps.setInt(1, antecedente.getId());
            ps.setInt(2, antecedente.getIdDoctor());
            ps.setDate(3, convertFromJAVADateToSQLDate(new java.util.Date()));
            ps.setString(4, antecedente.getFamiliar().getAntecedente());
            ps.setDate(5,convertFromJAVADateToSQLDate(antecedente.getFamiliar().getFechaDesde()));
            ps.setDate(6, convertFromJAVADateToSQLDate(antecedente.getFamiliar().getFechaHasta()));
            ps.setString(7, antecedente.getFamiliar().getOrigen());
            ps.setString(8, antecedente.getFamiliar().getObservaciones());
            ps.execute();
            closeCon(conexion, ps, rs);
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
    
    public static boolean nuevoAntecedenteMedicamento(Antecedente antecedente) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_ANTECEDENTE_MEDICAMENTO);
            ps.setInt(1, antecedente.getId());
            ps.setInt(2, antecedente.getIdDoctor());
            ps.setDate(3, convertFromJAVADateToSQLDate(new java.util.Date()));
            ps.setString(4, antecedente.getMedicamento().getMedicamento());
            ps.setDate(5,convertFromJAVADateToSQLDate(antecedente.getMedicamento().getFechaDesde()));
            ps.setDate(6, convertFromJAVADateToSQLDate(antecedente.getMedicamento().getFechaHasta()));
            ps.setString(7, antecedente.getMedicamento().getOrigen());
            ps.setString(8, antecedente.getMedicamento().getObservaciones());
            ps.execute();
            closeCon(conexion, ps, rs);
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
    


}
