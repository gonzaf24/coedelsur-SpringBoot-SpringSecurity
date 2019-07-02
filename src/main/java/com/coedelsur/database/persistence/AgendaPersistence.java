package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.coedelsur.database.connections.DBManager;
import com.coedelsur.model.Agenda;
import com.coedelsur.model.AgendaDia;
import com.coedelsur.model.AgendaTurno;
import com.coedelsur.model.Consulta;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringString;
import com.coedelsur.model.SelectStringValue;

public class AgendaPersistence extends UtilPersistence {

    public static List<AgendaDia> obtenerDiasAgendaDoctor(Doctor doc) throws Exception {
        List<AgendaDia> salida = new ArrayList<AgendaDia>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AgendaDia agendaDia;
        SimpleDateFormat dateFormatterA = new SimpleDateFormat("EEEE d MMMM", new Locale("es", "ES"));
        SimpleDateFormat dateFormatterB = new SimpleDateFormat("EEEE d MMMM , yyyy", new Locale("es", "ES"));
        Calendar cal = Calendar.getInstance();
        int index = 0;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_DIAS_AGENDA_DOCTOR);
            ps.setInt(1, doc.getId());
            ps.setDate(2, convertFromJAVADateToSQLDate(new java.util.Date()));
            rs = ps.executeQuery();
            while (rs.next()) {
                Date diaOut = rs.getDate("dia");
                cal.setTime(diaOut);
                int month = cal.get(Calendar.MONTH) + 1;
                String label;
                if (month == 12 || month == 1) {
                    label = new String(dateFormatterB.format(diaOut));
                } else {
                    label = new String(dateFormatterA.format(diaOut));
                }
                agendaDia = new AgendaDia(index, label, diaOut);
                index++;
                salida.add(agendaDia);
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

    public static List<AgendaTurno> obtenerTurnosAgendaDoctor(Doctor doc, AgendaDia agDia) throws Exception {
        List<AgendaTurno> salida = new ArrayList<AgendaTurno>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AgendaTurno agendaTurno;
        Calendar cal = Calendar.getInstance();
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_TURNOS_AGENDA_DOCTOR);
            ps.setInt(1, doc.getId());
            ps.setDate(2, convertFromJAVADateToSQLDate(agDia.getDia()));
            rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp diaOut = rs.getTimestamp("horadesde");
                Integer idConsulta = rs.getInt("id");
                cal.setTime(diaOut);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                String label = "";
                String houerLabel = "";
                if (hour < 10) {
                    houerLabel = "0" + hour;
                } else {
                    houerLabel = String.valueOf(hour);
                }
                if (minute >= 10) {
                    label = houerLabel + ":" + minute + " hrs.";
                } else {
                    label = houerLabel + ":0" + minute + " hrs.";
                }
                agendaTurno = new AgendaTurno(idConsulta, label, diaOut);
                salida.add(agendaTurno);
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

    public static boolean reservarTurno(AgendaTurno agTurno, Paciente paciente, ConsultaDoctor consutlaDoctor) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_RESERVAR_TURNO);
            ps.setInt(1, paciente.getId());
            ps.setInt(2, consutlaDoctor.getId());
            ps.setInt(3, agTurno.getId());
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

    public static boolean cancelarReservaTurno(AgendaTurno selectedAgendaTurno) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_CANCELAR_RESERVA_TURNO);
            ps.setInt(1, selectedAgendaTurno.getId());
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

    public static boolean turnoDisponible(AgendaTurno selectedAgendaTurno) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_TURNO_LIBRE);
            ps.setInt(1, selectedAgendaTurno.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                boolean libre = rs.getBoolean("libre");
                return libre;
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

    public static List<Consulta> obtenerConsultasPaciente(Integer idPaciente) throws Exception {
        List<Consulta> salida = new ArrayList<Consulta>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMMM , yyyy", new Locale("es", "ES"));
        Calendar cal = Calendar.getInstance();
        Consulta consulta = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_CONSULTAS_PACIENTE);
            ps.setInt(1, idPaciente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer idAgenda = rs.getInt("idAg");
                Date diaAg = rs.getDate("diaAg");
                Timestamp horaDesde = rs.getTimestamp("horaDesdeAg");
                Integer idConsultorio = rs.getInt("idConsultorioAg");
                Integer idConsulta = rs.getInt("idConsultaAg");
                Integer idDoctor = rs.getInt("idDoctorAg");
                Boolean libre = rs.getBoolean("libreAg");
                Integer precio = rs.getInt("precioCon");
                Integer idDocCon = rs.getInt("idDoctorCon");
                Integer idEspecialidad = rs.getInt("idEsp");
                String nombreEspecialidad = rs.getString("nombreEsp");
                Integer idTipoConsulta = rs.getInt("idTC");
                String nombreTipoConsulta = rs.getString("descripcionTC");
                String nombreDoctor = rs.getString("nombreDoc");
                String apellidosDoctor = rs.getString("apellidosDoc");
                String sexo = rs.getString("sexoDoc");
                String nombreConsultorio = rs.getString("nombreConsultorio");
                
                Agenda agenda = new Agenda();
                agenda.setId(idAgenda);
                agenda.setDia(diaAg);
                agenda.setHoraDesde(horaDesde);
                agenda.setIdConsultorio(idConsultorio);
                agenda.setIdConsulta(idConsulta);
                agenda.setIdDoctor(idDoctor);
                agenda.setIdPaciente(idPaciente);
                agenda.setLibre(libre);
                SelectStringValue especialidad = new SelectStringValue(nombreEspecialidad,idEspecialidad);
                SelectStringValue consultorio = new SelectStringValue(nombreConsultorio,idConsultorio);
                SelectStringValue tipoConsulta = new SelectStringValue(nombreTipoConsulta,idTipoConsulta);
                AgendaDia agendaDia = new AgendaDia();
                agendaDia.setDia(diaAg);
                agendaDia.setId(idAgenda);
                agendaDia.setLabel(new String(dateFormatter.format(diaAg)));
                AgendaTurno agendaTurno = new AgendaTurno();
                cal.setTime(horaDesde);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                String label = "";
                String houerLabel = "";
                if (hour < 10) {
                    houerLabel = "0" + hour;
                } else {
                    houerLabel = String.valueOf(hour);
                }
                if (minute >= 10) {
                    label = houerLabel + ":" + minute + " hrs.";
                } else {
                    label = houerLabel + ":0" + minute + " hrs.";
                }
                agendaTurno = new AgendaTurno(idConsulta, label, horaDesde);
                Doctor doctor = new Doctor();
                String sexoLabel = "";
                if (sexo.equals("H")) {
                    sexoLabel = "Dr.";
                } else {
                    sexoLabel = "Dra.";
                }
                doctor.setId(idDocCon);
                doctor.setNombre(nombreDoctor);
                doctor.setApellidos(apellidosDoctor);
                doctor.setSexo(new SelectStringString(sexoLabel,sexo));
                consulta = new Consulta(agenda, agendaDia, agendaTurno, tipoConsulta, especialidad, doctor, precio , consultorio);
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

    public static boolean confirmarPersistirTurnos(ArrayList<Agenda> listaAgenda) throws Exception {
        boolean salida = false;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        for (int i = 0; i < listaAgenda.size(); i++) {
            Agenda agenda = listaAgenda.get(i);
            try {
                conexion = DBManager.getDBConection();
                ps = conexion.prepareStatement(DBManager.CLI_QUERY_INSERT_AGENDA);
                ps.setInt(1, agenda.getIdDoctor());
                ps.setInt(2, agenda.getIdConsultorio());
                ps.setDate(3, convertFromJAVADateToSQLDate(agenda.getDia()));
                ps.setTimestamp(4, convertFromJAVADateToSQLTimeStampAUX(agenda.getDia(),agenda.getHoraDesde()));
                ps.setTimestamp(5, convertFromJAVADateToSQLTimeStampAUX(agenda.getDia(),agenda.getHoraHasta()));
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
        }
        return salida;
    }

    public static ArrayList<Agenda> obtenerAgendaPorDias(ArrayList<java.util.Date> arrayList) throws Exception {
        ArrayList<Agenda> salida = new ArrayList<Agenda>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SimpleDateFormat dateFormatDia = new SimpleDateFormat("EEEE d MMMM , yyyy", new Locale("es", "ES"));
        SimpleDateFormat dateFormatHora = new SimpleDateFormat ("HH:mm");
        try {
            conexion = DBManager.getDBConection();
            int index = 1;
            String consulta = DBManager.CLI_QUERY_GET_AGENDA_POR_DIAS;
            for (int i = 0; i < arrayList.size(); i++) {
                if (i == 0) {
                    consulta = consulta + " dia = ? ";
                } else {
                    consulta = consulta + " or dia = ? ";
                }
            }
            ps = conexion.prepareStatement(consulta);
            for (int i = 0; i < arrayList.size(); i++) {
                Date sqlDate = convertFromJAVADateToSQLDate(arrayList.get(i));
                ps.setDate(index, sqlDate);
                index++;
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer idDoctor = rs.getInt("id_doctor");
                Integer idPaciente = rs.getInt("id_paciente");
                Integer idConsultorio = rs.getInt("id_consultorio");
                Integer idConsulta = rs.getInt("id_consulta");
                Date dia = rs.getDate("dia");
                Timestamp horaDesde = rs.getTimestamp("horadesde");
                Timestamp horaHasta = rs.getTimestamp("horahasta");
                Boolean libre = rs.getBoolean("libre");
                Boolean aceptado = rs.getBoolean("aceptado");
                String labelDia = dateFormatDia.format(dia);
                String labelHoraDesde = dateFormatHora.format(horaDesde);
                String labelHoraHasta = dateFormatHora.format(horaHasta);
                salida.add(new Agenda(id, idDoctor, idPaciente, idConsultorio, idConsulta, dia, horaDesde, horaHasta, libre, aceptado, labelDia , labelHoraDesde , labelHoraHasta));
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

    public static ArrayList<Agenda> obtenerAgendaDoctor(Integer idDoctor) throws Exception {
        
        ArrayList<Agenda> salida = new ArrayList<Agenda>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SimpleDateFormat dateDiaFormat = new SimpleDateFormat("dd/M/yyyy");
        SimpleDateFormat dateFormatHora = new SimpleDateFormat ("HH:mm");
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_AGENDA_POR_ID_DOCTOR);
            ps.setInt(1, idDoctor);
            ps.setDate(2, convertFromJAVADateToSQLDate(new java.util.Date()));
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer idAg = rs.getInt("idAg");
                Date diaAg = rs.getDate("diaAg");
                Integer idPaciente = rs.getInt("idPacienteAg");
                Timestamp horaDesde = rs.getTimestamp("horaDesdeAg");
                Timestamp horaHasta = rs.getTimestamp("horaHastaAg");
                Integer idConsulta = rs.getInt("idConsultaAg");
                Boolean libre = rs.getBoolean("libreAg");
                Boolean aceptado = rs.getBoolean("aceptadoAg");
                Integer idConsultorio = rs.getInt("idConsultorioAg");
                String descConsultorio = rs.getString("descripcionCon");
                
                String labelDia = dateDiaFormat.format(diaAg);
                String labelHoraDesde = dateFormatHora.format(horaDesde);
                String labelHoraHasta  = dateFormatHora.format(horaHasta);

                if(idPaciente==0){
                    idPaciente=null;
                }
                
                Agenda agenda = new Agenda(idAg, idDoctor , idPaciente, idConsultorio , idConsulta ,diaAg , horaDesde , horaHasta, libre , aceptado, labelDia , labelHoraDesde, labelHoraHasta);
                
                SelectStringValue consultorio = new SelectStringValue(descConsultorio, idConsultorio);
                agenda.setConsultorio(consultorio);
             
                salida.add(agenda);
                
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
    
  
    public static ArrayList<Agenda> obtenerAgendaHistoricaDoctor(Integer idDoctorParam) throws Exception {
        ArrayList<Agenda> salida = new ArrayList<Agenda>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SimpleDateFormat dateDiaFormat = new SimpleDateFormat("dd/M/yyyy");
        SimpleDateFormat dateFormatHora = new SimpleDateFormat ("HH:mm");
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_AGENDA_HOSTORICA_POR_ID_DOCTOR);
            ps.setInt(1, idDoctorParam);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer idAg = rs.getInt("idAg");
                Date diaAg = rs.getDate("diaAg");
                Integer idPaciente = rs.getInt("idPacienteAg");
                Timestamp horaDesde = rs.getTimestamp("horaDesdeAg");
                Timestamp horaHasta = rs.getTimestamp("horaHastaAg");
                Integer idDoctor = rs.getInt("idDoctorAg");
                Integer idConsulta = rs.getInt("idConsultaAg");
                Boolean libre = rs.getBoolean("libreAg");
                Boolean aceptado = rs.getBoolean("aceptadoAg");
                //Integer idTipoConsulta = rs.getInt("idTC");
                //String nombreTipoConsulta = rs.getString("descripcionTC");
                Integer idConsultorio = rs.getInt("idConsultorioAg");
                String descConsultorio = rs.getString("descripcionCon");
                //Integer idEsp = rs.getInt("idEsp");
               // String nombreEspecialidad = rs.getString("nombreEsp");
                
                String labelDia = dateDiaFormat.format(diaAg);
                String labelHoraDesde = dateFormatHora.format(horaDesde);
                String labelHoraHasta  = dateFormatHora.format(horaHasta);
                
                if(idPaciente==0){
                    idPaciente=null;
                }

                Agenda agenda = new Agenda(idAg, idDoctor , idPaciente, idConsultorio , idConsulta ,diaAg , horaDesde , horaHasta, libre , aceptado, labelDia , labelHoraDesde, labelHoraHasta);
                
                SelectStringValue consultorio = new SelectStringValue(descConsultorio, idConsultorio);
                agenda.setConsultorio(consultorio);
                
//                SelectStringValue tipoConsulta = new SelectStringValue(nombreTipoConsulta,idTipoConsulta,Boolean.TRUE);
//                agenda.setTipoConsulta(tipoConsulta);
//                
//                SelectStringValue especialidad = new SelectStringValue(nombreEspecialidad,idEsp,Boolean.TRUE);
//                agenda.setEspecialidad(especialidad);
                
                salida.add(agenda);
                
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

    public static boolean eliminarTurno(Integer idTurno) throws Exception {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_DELETE_TURNO);
            ps.setInt(1, idTurno);
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
    
    
    public static ArrayList<Agenda> obtenerMiAgenda(Integer idDoctor) throws Exception {
        
        ArrayList<Agenda> salida = new ArrayList<Agenda>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SimpleDateFormat dateDiaFormat = new SimpleDateFormat("dd/M/yyyy");
        SimpleDateFormat dateFormatHora = new SimpleDateFormat ("HH:mm");
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_MI_AGENDA_POR_ID_DOCTOR);
            ps.setInt(1, idDoctor);
            ps.setDate(2, convertFromJAVADateToSQLDate(new java.util.Date()));
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Integer idAg = rs.getInt("idAg");
                Date diaAg = rs.getDate("diaAg");
                Integer idPaciente = rs.getInt("idPacienteAg");
                Timestamp horaDesde = rs.getTimestamp("horaDesdeAg");
                Timestamp horaHasta = rs.getTimestamp("horaHastaAg");
                Integer idConsulta = rs.getInt("idConsultaAg");
                Boolean libre = rs.getBoolean("libreAg");
                Boolean aceptado = rs.getBoolean("aceptadoAg");
                Integer idTipoConsulta = rs.getInt("idTC");
                String nombreTipoConsulta = rs.getString("descripcionTC");
                Integer idConsultorio = rs.getInt("idConsultorioAg");
                String descConsultorio = rs.getString("descripcionCon");
                Integer idEsp = rs.getInt("idEsp");
                String nombreEspecialidad = rs.getString("nombreEsp");
                
                Integer ciPac = rs.getInt("ciPac");    
                String nombrePac = rs.getString("nombrePac");
                String apellidosPac = rs.getString("apellidosPac");
                String telefonoPac = rs.getString("telefonoPac");
                String domicilioPac = rs.getString("domicilioPac");
                Date fechaNacimientoPac = rs.getDate("fechaNacimientoPac");
                Integer idDepartamentoPac = rs.getInt("idDepartamentoPac");
                
                String labelDia = dateDiaFormat.format(diaAg);
                String labelHoraDesde = dateFormatHora.format(horaDesde);
                String labelHoraHasta  = dateFormatHora.format(horaHasta);
 
                Agenda agenda = new Agenda(idAg, idDoctor , idPaciente, idConsultorio , idConsulta ,diaAg , horaDesde , horaHasta, libre , aceptado, labelDia , labelHoraDesde, labelHoraHasta);
                
                Paciente paciente = new Paciente(idPaciente, ciPac, nombrePac, apellidosPac, telefonoPac, idDepartamentoPac, domicilioPac, fechaNacimientoPac);
                agenda.setPaciente(paciente);
                
                SelectStringValue consultorio = new SelectStringValue(descConsultorio, idConsultorio);
                agenda.setConsultorio(consultorio);
                
                SelectStringValue tipoConsulta = new SelectStringValue( nombreTipoConsulta,idTipoConsulta, Boolean.TRUE);
                agenda.setTipoConsulta(tipoConsulta);
                
                SelectStringValue especialidad = new SelectStringValue(nombreEspecialidad, idEsp, Boolean.TRUE);
                agenda.setEspecialidad(especialidad);
                
                salida.add(agenda);
                
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
    
    public static ArrayList<Agenda> obtenerMiAgendaHistorico(Integer idDoctor) throws Exception {
        
        ArrayList<Agenda> salida = new ArrayList<Agenda>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SimpleDateFormat dateDiaFormat = new SimpleDateFormat("dd/M/yyyy");
        SimpleDateFormat dateFormatHora = new SimpleDateFormat ("HH:mm");
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_MI_AGENDA_HISTORICO_POR_ID_DOCTOR);
            ps.setInt(1, idDoctor);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Integer idAg = rs.getInt("idAg");
                Date diaAg = rs.getDate("diaAg");
                Integer idPaciente = rs.getInt("idPacienteAg");
                Timestamp horaDesde = rs.getTimestamp("horaDesdeAg");
                Timestamp horaHasta = rs.getTimestamp("horaHastaAg");
                Integer idConsulta = rs.getInt("idConsultaAg");
                Boolean libre = rs.getBoolean("libreAg");
                Boolean aceptado = rs.getBoolean("aceptadoAg");
                Integer idTipoConsulta = rs.getInt("idTC");
                String nombreTipoConsulta = rs.getString("descripcionTC");
                Integer idConsultorio = rs.getInt("idConsultorioAg");
                String descConsultorio = rs.getString("descripcionCon");
                Integer idEsp = rs.getInt("idEsp");
                String nombreEspecialidad = rs.getString("nombreEsp");
                
                Integer ciPac = rs.getInt("ciPac");    
                String nombrePac = rs.getString("nombrePac");
                String apellidosPac = rs.getString("apellidosPac");
                String telefonoPac = rs.getString("telefonoPac");
                String domicilioPac = rs.getString("domicilioPac");
                Date fechaNacimientoPac = rs.getDate("fechaNacimientoPac");
                Integer idDepartamentoPac = rs.getInt("idDepartamentoPac");
                
                String labelDia = dateDiaFormat.format(diaAg);
                String labelHoraDesde = dateFormatHora.format(horaDesde);
                String labelHoraHasta  = dateFormatHora.format(horaHasta);
 
                Agenda agenda = new Agenda(idAg, idDoctor , idPaciente, idConsultorio , idConsulta ,diaAg , horaDesde , horaHasta, libre , aceptado, labelDia , labelHoraDesde, labelHoraHasta);
                
                Paciente paciente = new Paciente(idPaciente, ciPac, nombrePac, apellidosPac, telefonoPac, idDepartamentoPac, domicilioPac, fechaNacimientoPac);
                agenda.setPaciente(paciente);
                
                SelectStringValue consultorio = new SelectStringValue(descConsultorio, idConsultorio);
                agenda.setConsultorio(consultorio);
                
                SelectStringValue tipoConsulta = new SelectStringValue(nombreTipoConsulta,idTipoConsulta,Boolean.TRUE);
                agenda.setTipoConsulta(tipoConsulta);
                
                SelectStringValue especialidad = new SelectStringValue(nombreEspecialidad,idEsp,Boolean.TRUE);
                agenda.setEspecialidad(especialidad);
                
                salida.add(agenda);
                
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

    public static SelectStringValue obtenerConsultorioAgendaTurno(AgendaTurno selectedAgendaTurno) throws Exception  {
        SelectStringValue salida = new SelectStringValue();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_CONSULTORIO_AGENDA_TURNO);
            ps.setInt(1, selectedAgendaTurno.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer idConsultorio = rs.getInt("idConsultorio");
                String descConsultorio = rs.getString("descripcionCon");
                salida = new SelectStringValue(descConsultorio, idConsultorio);
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

    public static Agenda obtenerAgendaDoctorPaciente(Agenda aux) throws Exception {
        Agenda salida = new Agenda();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SimpleDateFormat dateDiaFormat = new SimpleDateFormat("dd/M/yyyy");
        SimpleDateFormat dateFormatHora = new SimpleDateFormat ("HH:mm");
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_AGENDA_PACIENTE);
            ps.setInt(1, aux.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Date diaAg = rs.getDate("diaAg");
                Integer idPaciente = rs.getInt("idPacienteAg");
                Timestamp horaDesde = rs.getTimestamp("horaDesdeAg");
                Timestamp horaHasta = rs.getTimestamp("horaHastaAg");
                Integer idConsulta = rs.getInt("idConsultaAg");
                Boolean libre = rs.getBoolean("libreAg");
                Boolean aceptado = rs.getBoolean("aceptadoAg");
                Integer idTipoConsulta = rs.getInt("idTC");
                String nombreTipoConsulta = rs.getString("descripcionTC");
                Integer idConsultorio = rs.getInt("idConsultorioAg");
                String descConsultorio = rs.getString("descripcionCon");
                Integer idEsp = rs.getInt("idEsp");
                Integer idDoctor = rs.getInt("idDoctorAg");
                String nombreEspecialidad = rs.getString("nombreEsp");
                
                String labelDia = dateDiaFormat.format(diaAg);
                String labelHoraDesde = dateFormatHora.format(horaDesde);
                String labelHoraHasta  = dateFormatHora.format(horaHasta);

                if(idPaciente==0){
                    idPaciente=null;
                }
                
                salida = new Agenda(aux.getId(), idDoctor , idPaciente, idConsultorio , idConsulta ,diaAg , horaDesde , horaHasta, libre , aceptado, labelDia , labelHoraDesde, labelHoraHasta);
                
                SelectStringValue consultorio = new SelectStringValue(descConsultorio, idConsultorio);
                salida.setConsultorio(consultorio);
                
                SelectStringValue tipoConsulta = new SelectStringValue(nombreTipoConsulta,idTipoConsulta,Boolean.TRUE);
                salida.setTipoConsulta(tipoConsulta);
                
                SelectStringValue especialidad = new SelectStringValue(nombreEspecialidad,idEsp,Boolean.TRUE);
                salida.setEspecialidad(especialidad);
                
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
