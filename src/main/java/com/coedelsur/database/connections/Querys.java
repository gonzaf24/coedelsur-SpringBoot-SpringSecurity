package com.coedelsur.database.connections;

public class Querys {
	
    
	//login
	public static String CLI_QUERY_GET_DEPARTAMENTOS      = "SELECT * FROM departamento where activo= true order by descripcion asc";
	public static String CLI_QUERY_GET_USUARIO            = "SELECT * FROM usuarios WHERE usuario = ? ";
	public static String CLI_QUERY_GET_USUARIOBYID            = "SELECT * FROM usuarios WHERE id = ? ";
    public static String CLI_QUERY_GET_USUARIO_HABILITADO = "SELECT * FROM usuarios WHERE usuario = ? AND pass = ? and habilitado = true ";
   
    public static String CLI_QUERY_OBT_PSSWRD    = "SELECT pass FROM usuarios WHERE id = ? ";
    public static String CLI_QUERY_UPDATE_PSSWRD = "UPDATE usuarios SET pass = ? WHERE id = ? ";
    public static String CLI_QUERY_OBT_USUARIO   = "SELECT * FROM usuarios WHERE id = ? ";
    public static String CLI_QUERY_EXISTE_EMAIL  = "SELECT * FROM usuarios WHERE usuario = ? ";
    
    public static String CLI_QUERY_GET_PRIMER_CAPITULO_DIAGNOSTICO  = "select * from cie10  where grp10 in ('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21') and id10 not like '%|%' ";
    public static String CLI_QUERY_GET_SEGUNDO_CAPITULO_DIAGNOSTICO = "select * from cie10  where id10 like ? and grp10 = ? ";
    public static String CLI_QUERY_GET_TERCER_CAPITULO_DIAGNOSTICO  = "select * from cie10 where grp10 like ? ";
    public static String CLI_QUERY_GET_CUARTO_CAPITULO_DIAGNOSTICO  = "select * from cie10  where id10 like ? and LENGTH(id10) > (3)";
    public static String CLI_QUERY_GET_DIAGNOSTICO_POR_TEXTO        = "select * from cie10  where dec10 ilike ? and LENGTH(id10) > (3) limit 25";
    public static String CLI_QUERY_GET_DIAGNOSTICO_POR_CODIGO       = "select * from cie10  where id10 ilike ? and LENGTH(id10) > (3) limit 25";
    
    public static String CLI_QUERY_GET_DIAGNOSTICO_LIBRE_MAS_USADO = "SELECT dec10 as valor, COUNT(dec10) from diagnostico where id10 = '0000' group by dec10 order by count desc limit 10";
    
    public static String CLI_QUERY_GET_PACIENTE_BY_ID =  "SELECT    d.id               AS idD, "
                                                                + " d.ci               AS ciD,"
                                                                + " d.nombre           AS nombreD,"
                                                                + " d.apellidos        AS apellidosD,"
                                                                + " d.telefono         AS telefonoD,"
                                                                + " d.domicilio        AS domicilioD,"
                                                                + " d.id_departamento  AS idDepartamentoD,"
                                                                + " d.fecha_nacimiento AS fechaNaciemientoD,"
                                                                + " d.foto             AS fotoD,"
                                                                + " dep.descripcion    AS nombreDep ,"
                                                                + " u.usuario          AS email "
                                                                + " FROM pacientes d , departamento dep, usuarios u "
                                                                + " WHERE d.id = ? " 
                                                                + " and d.id = u.id "
                                                                + " and d.id_departamento = dep.id ";
    
    public static String CLI_QUERY_GET_HISTORIA_CLINICA_BY_ID_PACIENTE =  "SELECT   d.id               AS idD, "
                                                                                + " d.ci_paciente      AS ciPacienteD,"
                                                                                + " d.id_paciente      AS idPacienteD,"
                                                                                + " d.id_episodio      AS idEpisodioD,"
                                                                                + " d.fecha_creacion   AS fechaCreacionD "
                                                                                + " FROM historia_clinica d "
                                                                                + " WHERE d.id_paciente = ? " ;

    
    
    public static String CLI_QUERY_INSERT_LOGUEO_USUARIO =  "INSERT INTO usuarios_auditoria (usuario, fecha) VALUES (?,?)";
    
    public static String CLI_QUERY_SELECT_LOGUEO_USUARIO =  "SELECT * FROM usuarios_auditoria  order by id desc limit 10";

    public static String CLI_QUERY_INSERT_USUARIO  =  "INSERT INTO usuarios (usuario, pass, tipo,role,habilitado,fecha_creacion) VALUES (?,?,?,?,?,?)";
    
    public static String CLI_QUERY_INSERT_DOCTOR   =  "INSERT INTO doctores (id,ci,id_especialidad,nombre,apellidos,telefono,domicilio,id_departamento,fecha_creacion,activo,sexo,foto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public static String CLI_QUERY_INSERT_PACIENTE =  "INSERT INTO pacientes (id, ci, nombre, apellidos, telefono,id_departamento,domicilio,fecha_nacimiento,fecha_creacion) VALUES (?,?,?,?,?,?,?,?,?)";
    
    public static String CLI_QUERY_INSERT_HISTORIA_CLINICA =  "INSERT INTO historia_clinica (id_paciente, ci_paciente, id_episodio, fecha_creacion,habilitado) VALUES (?,?,?,?,?)";
    
    public static String CLI_QUERY_INSERT_CONSULTA_MEDICA  =  "INSERT INTO consulta_medica (id_paciente, id_doctor, fecha_creacion,repetir_medicamento ,motivo,anemesis,examen_fisico,peso_kgs,talla_cms,imc,cintura_cms,diagnostico,conducta) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static String CLI_QUERY_INSERT_DIAGNOSTICO_DE_CONSULTA_MEDICA =  "INSERT INTO diagnostico (id_doctor,id_paciente,id_consulta,fecha_creacion,tipo,descripcion,id10,dec10,grp10) VALUES (?,?,?,?,?,?,?,?,?)";

    public static String CLI_QUERY_EDITAR_CONSULTA_MEDICA   = "UPDATE consulta_medica SET repetir_medicamento = ? ,"
                                                                                        + "motivo = ?,"
                                                                                        + "anemesis = ?,"
                                                                                        + "examen_fisico = ?,"
                                                                                        + "peso_kgs = ?,"
                                                                                        + "talla_cms = ?,"
                                                                                        + "imc = ?,"
                                                                                        + "cintura_cms = ?,"
                                                                                        + "diagnostico = ?,"
                                                                                        + "conducta= ?"
                                                                                        + " WHERE id = ? "; 
    
    public static String CLI_QUERY_EDITAR_DIAGNOSTICO_DE_CONSULTA_MEDICA = "UPDATE diagnostico SET id_doctor = ? ,"
                                                                                                + "id_paciente = ?,"
                                                                                                + "tipo = ?,"
                                                                                                + "descripcion = ?,"
                                                                                                + "id10 = ?,"
                                                                                                + "dec10 = ?,"
                                                                                                + "grp10 = ? "
                                                                                                + " WHERE id_consulta = ? "; 
    
    public static String CLI_QUERY_GET_CONSULTAS_MEDICAS_BY_ID_PACIENTE    =  "SELECT * FROM consulta_medica WHERE id_paciente = ? order by fecha_creacion desc"; ;
    
    public static String CLI_QUERY_ELIMINAR_DIAGNOSTICO_DE_CONSULTA_MEDICA = "DELETE FROM diagnostico WHERE id_consulta = ?";
    
    public static String CLI_QUERY_ELIMINAR_CONSULTA_MEDICA = "DELETE FROM consulta_medica WHERE id = ?";
    
    public static String CLI_QUERY_INSERT_ANTECEDENTE       =  "INSERT INTO antecedente (id_paciente, id_doctor, fecha_creacion) VALUES (?,?,?)";
    
    public static String CLI_QUERY_SELECT_ANTECEDENTES_BY_ID_PACIENTE = "SELECT     antecedente.id  AS antId,"
                                                                                + " antecedente.id_paciente AS antIdPaciente,"
                                                                                + " antecedente.id_doctor AS antIdDoctor,"
                                                                                + " antecedente.fecha_creacion AS antFechaCreacion,"
                                                                                
                                                                                + " antecedente_personal.id_doctor AS antPerIdDoctor,"
                                                                                + " antecedente_personal.fecha_creacion AS antPerFechaCreacion,"
                                                                                + " antecedente_personal.antecedente AS antPerAntecedente,"
                                                                                + " antecedente_personal.fecha_desde AS antPerFechaDesde,"
                                                                                + " antecedente_personal.fecha_hasta AS antPerFechaHasta,"
                                                                                + " antecedente_personal.origen AS antPerOrigen,"
                                                                                + " antecedente_personal.observaciones antPerObservaciones,"
                                                                    
                                                                                + " antecedente_familiar.id_doctor AS antFamIdDoctor,"
                                                                                + " antecedente_familiar.fecha_creacion AS antFamFechaCreacion,"
                                                                                + " antecedente_familiar.antecedente AS antFamAntecedente,"
                                                                                + " antecedente_familiar.fecha_desde AS antFamFechaDesde,"
                                                                                + " antecedente_familiar.fecha_hasta AS antFamFechaHasta,"
                                                                                + " antecedente_familiar.origen AS antFamOrigen,"
                                                                                + " antecedente_familiar.observaciones AS antFamObservaciones,"                                                                                
                                                                                                                                                                    
                                                                                + " antecedente_medicamento.id_doctor AS medicIdDoctor,"
                                                                                + " antecedente_medicamento.fecha_creacion AS medicFechaCreacion,"
                                                                                + " antecedente_medicamento.medicamento AS medicMedicamento,"
                                                                                + " antecedente_medicamento.fecha_desde AS medicFechaDesde,"
                                                                                + " antecedente_medicamento.fecha_hasta AS medicFechaHasta,"
                                                                                + " antecedente_medicamento.origen AS medicOrigen,"
                                                                                + " antecedente_medicamento.observaciones AS medicObservaciones "
                                                                                + " FROM antecedente "
                                                                                + " LEFT JOIN antecedente_personal ON antecedente_personal.id = antecedente.id "
                                                                                + " LEFT JOIN antecedente_familiar ON antecedente_familiar.id = antecedente.id "
                                                                                + " LEFT JOIN antecedente_medicamento ON antecedente_medicamento.id = antecedente.id"
                                                                                + " WHERE "
                                                                                + " antecedente.id_paciente = ? order by antecedente.fecha_creacion desc ";

    public static String CLI_QUERY_ELIMINAR_ANTECEDENTE= "DELETE FROM antecedente WHERE id = ? ";
    
    public static String CLI_QUERY_INSERT_ANTECEDENTE_PERSONAL =  "INSERT INTO antecedente_personal (id, id_doctor, fecha_creacion,antecedente,fecha_desde,fecha_hasta,origen,observaciones) VALUES (?,?,?,?,?,?,?,?)";
    
    public static String CLI_QUERY_EDITAR_ANTECEDENTE_PERSONAL = "UPDATE antecedente_personal SET antecedente  = ? ,"
                                                                                                + "fecha_desde = ?,"
                                                                                                + "fecha_hasta = ?,"
                                                                                                + "origen = ?,"
                                                                                                + "observaciones  = ?"
                                                                                                + "  WHERE id = ? ";
                                                                                                                                                                            
    public static String CLI_QUERY_ELIMINAR_ANTECEDENTE_PERSONAL= "DELETE FROM antecedente_personal WHERE id = ?";
    
    public static String CLI_QUERY_INSERT_ANTECEDENTE_FAMILIAR  =  "INSERT INTO antecedente_familiar (id, id_doctor, fecha_creacion,antecedente,fecha_desde,fecha_hasta,origen,observaciones) VALUES (?,?,?,?,?,?,?,?)";
    
    public static String CLI_QUERY_EDITAR_ANTECEDENTE_FAMILIAR  = "UPDATE antecedente_familiar SET antecedente  = ? ,"
                                                                                                + "fecha_desde = ?,"
                                                                                                + "fecha_hasta = ?,"
                                                                                                + "origen = ?,"
                                                                                                + "observaciones  = ?"
                                                                                                + "  WHERE id = ? ";
    
    public static String CLI_QUERY_ELIMINAR_ANTECEDENTE_FAMILIAR  = "DELETE FROM antecedente_familiar WHERE id = ?";
    
    public static String CLI_QUERY_INSERT_ANTECEDENTE_MEDICAMENTO =  "INSERT INTO antecedente_medicamento (id, id_doctor, fecha_creacion,medicamento,fecha_desde,fecha_hasta,origen,observaciones) VALUES (?,?,?,?,?,?,?,?)";
   
    public static String CLI_QUERY_EDITAR_ANTECEDENTE_MEDICAMENTO = "UPDATE antecedente_medicamento SET medicamento  = ? ,"
                                                                                                + "fecha_desde = ?,"
                                                                                                + "fecha_hasta = ?,"
                                                                                                + "origen = ?,"
                                                                                                + "observaciones  = ?"
                                                                                                + "  WHERE id = ? ";

    public static String CLI_QUERY_ELIMINAR_ANTECEDENTE_MEDICAMENTO = "DELETE FROM antecedente_medicamento WHERE id = ?";
    
    
                                
    public static String CLI_QUERY_GET_TIPO_CONSULTAS       = "SELECT * FROM tipo_consulta where habilitado=true order by descripcion asc";
    
    public static String CLI_QUERY_GET_TIPO_CONSULTAS_TODAS = "SELECT * FROM tipo_consulta where order by descripcion asc";
    
    public static String CLI_QUERY_GET_TIPO_CONSULTAS_SOLICITAR_CITA = "SELECT DISTINCT ON (c.id_tipo_consulta) c.id_tipo_consulta as idTipoCons, "
                                                                                    + " tc.descripcion as descTipoCons, "
                                                                                    + " tc.habilitado  as habilitadoTipoCons, "
                                                                                    + " c.id           as idConsulta "
                                                                                    + " FROM consulta c, tipo_consulta tc "
                                                                                    + " where c.id_tipo_consulta = tc.id "
                                                                                    + " and tc.habilitado=true ";
    
    public static String CLI_QUERY_GET_ESPECIALIDADES       = "SELECT * FROM especialidades where habilitado=true order by nombre asc";
    public static String CLI_QUERY_GET_ESPECIALIDADES_TODAS = "SELECT * FROM especialidades where order by nombre asc";
    
    public static String CLI_QUERY_GET_CONSULTORIOS         =  "SELECT * FROM consultorios WHERE activo = true";
    public static String CLI_QUERY_GET_CONSULTORIOS_TODOS   =  "SELECT * FROM consultorios order by descripcion asc ";
    
    public static String CLI_QUERY_GET_ESPECIALIDADES_POR_TIPO_CONSULTA_SOLICITAR_CITA = "SELECT DISTINCT c.id_especialidad  AS idEsp, "
                                                                                        + " e.nombre          AS nombre,"
                                                                                        + " e.habilitado      AS habilitado "
                                                                                        + " FROM consulta c, especialidades e "
                                                                                        + " WHERE c.id_tipo_consulta=? "
                                                                                        + " and c.id_especialidad = e.id "
                                                                                        + " order by e.nombre asc";
    
    
    public static String CLI_QUERY_GET_CONSULTA_SOLICITAR_CITA = "SELECT c.id                AS idCon, "
                                                                     + " c.descripcion       AS descripcionCon,"
                                                                     + " c.precio            AS precioCon,"
                                                                     + " c.id_doctor         AS idDoctorCon,"
                                                                     + " c.id_tipo_consulta  AS idTipoConsultaCon,"
                                                                     + " c.id_especialidad   AS idEspecialidadCon,"
                                                                     + " c.habilitado        AS  habilitadoCon,"
                                                                     + " tc.descripcion      AS descripcionTC, "
                                                                     + " e.nombre            AS nombreEsp "
                                                                     + " FROM consulta c , tipo_consulta tc , especialidades e "
                                                                     + " WHERE c.id_tipo_consulta = tc.id "
                                                                     + "   and c.id_especialidad  = e.id "
                                                                     + "   and c.id_tipo_consulta = ? "
                                                                     + "   and c.id_especialidad = ? ";
           
                          
    public static String CLI_QUERY_GET_DIAS_AGENDA_DOCTOR = "SELECT DISTINCT dia FROM agenda "
                                                                            + " WHERE id_doctor=? and dia >= ? "
                                                                            + " and libre = true "
                                                                            + " order by dia asc ";
    
    public static String CLI_QUERY_GET_TURNOS_AGENDA_DOCTOR = "SELECT * FROM agenda "
                                                                    + " WHERE id_doctor= ? and dia = ? "
                                                                    + " and libre = true "
                                                                    + " order by horadesde asc ";
    
    public static String CLI_QUERY_GET_DOCTORES = "SELECT  doc.id           AS idDoc, "
                                                       + " doc.nombre       AS nombreDoc, "
                                                       + " doc.apellidos    AS apellidosDoc, "
                                                       + " doc.ci           AS ciDoc, "
                                                       + " doc.sexo         AS sexoDoc, "
                                                       + " doc.foto         AS fotoDoc, "
                                                       + " e.nombre         AS nombreEsp "
                                                       + " FROM doctores doc, especialidades e "
                                                       + " where doc.id_especialidad= ? "
                                                       + " and doc.id_especialidad = e.id "
                                                       + " order by doc.nombre, doc.apellidos asc";
    
    public static String CLI_QUERY_RESERVAR_TURNO = "UPDATE agenda SET libre = false , "
                                                                     + " id_paciente = ?, "
                                                                     + " id_consulta = ? "
                                                                     + " WHERE id = ? ";
    
    public static String CLI_QUERY_TURNO_LIBRE = "SELECT libre FROM agenda WHERE id = ? ";

    public static String CLI_QUERY_CANCELAR_RESERVA_TURNO = "UPDATE agenda SET libre = true , "
                                                                    + " id_paciente = null,"
                                                                    + " id_consulta = null "
                                                                    + " WHERE id = ? ";
    
    public static String CLI_QUERY_GET_CONSULTAS_PACIENTE = "SELECT     a.id             AS idAg, "
                                                                    + " a.dia            AS diaAg,"
                                                                    + " a.horaDesde      AS horaDesdeAg,"
                                                                    + " a.id_consultorio AS idConsultorioAg,"
                                                                    + " a.id_consulta    AS idConsultaAg,"
                                                                    + " a.id_doctor      AS idDoctorAg,"
                                                                    + " a.libre          AS libreAg,"
                                                                    + " c.precio         AS precioCon, "
                                                                    + " c.id_doctor      AS idDoctorCon, "
                                                                    + " e.id             AS idEsp, "
                                                                    + " e.nombre         AS nombreEsp, "
                                                                    + " tc.id            AS idTC, "
                                                                    + " tc.descripcion   AS descripcionTC, "
                                                                    + " doc.nombre       AS nombreDoc, "
                                                                    + " doc.apellidos    AS apellidosDoc, "
                                                                    + " doc.sexo         AS sexoDoc, "
                                                                    + " cons.descripcion AS nombreConsultorio "
                                                                    + " FROM agenda a , consulta c , especialidades e , tipo_consulta tc , doctores doc , consultorios cons"
                                                                    + " WHERE a.id_paciente = ? "
                                                                    + " and a.id_consulta = c.id "
                                                                    + " and c.id_especialidad = e.id "
                                                                    + " and c.id_tipo_consulta = tc.id "
                                                                    + " and a.id_doctor = doc.id "
                                                                    + " and a.id_consultorio = cons.id "
                                                                    + " order by a.dia desc";
    
    public static String CLI_QUERY_INSERT_FILES_PACIENTE =  "INSERT INTO pacientes_file (id_paciente,id_doctor,descripcion,archivo,tipo_archivo,fecha_creacion,activo) VALUES (?,?,?,?,?,?,?)";
    
    public static String CLI_QUERY_GET_FILES_PACIENTE    = "SELECT * FROM pacientes_file WHERE id_paciente= ? and activo = true order by fecha_creacion desc ";
    
    public static String CLI_QUERY_GET_FILE_PACIENTE     = "SELECT * FROM pacientes_file WHERE id= ? and activo = true ";
    
    public static String CLI_QUERY_ELIMINAR_FILE    = "DELETE FROM pacientes_file WHERE id = ?";
    
    public static String CLI_QUERY_GET_DOCTOR_BY_ID =  "SELECT  d.id              AS idD, "
                                                            + " d.ci              AS ciD,"
                                                            + " d.nombre          AS nombreD,"
                                                            + " d.apellidos       AS apellidosD,"
                                                            + " d.telefono        AS telefonoD,"
                                                            + " d.domicilio       AS domicilioD,"
                                                            + " d.id_departamento AS idDepartamentoD,"
                                                            + " d.id_especialidad AS idEspecialidadD,"
                                                            + " d.activo          AS activoD,"
                                                            + " d.sexo            AS sexoD, "
                                                            + " d.foto            AS fotoD, "
                                                            + " e.nombre          as nombreE, "
                                                            + " dep.descripcion   as nombreDep "
                                                            + " FROM doctores d , especialidades e , departamento dep "
                                                            + " WHERE d.id = ? " 
                                                            + " and d.id_especialidad = e.id "
                                                            + " and d.id_departamento = dep.id ";
            
    public static String CLI_QUERY_INSERT_TIPO_CONSULTA =  "INSERT INTO tipo_consulta (descripcion) VALUES (?)";
    public static String CLI_QUERY_INSERT_ESPECIALIDAD  =  "INSERT INTO especialidades (nombre) VALUES (?)";
    public static String CLI_QUERY_INSERT_CONSULTORIO   =  "INSERT INTO consultorios (descripcion) VALUES (?)";
    
    public static String CLI_QUERY_INSERT_CONSULTA   =  "INSERT INTO consulta (descripcion,precio,id_tipo_consulta,id_especialidad,habilitado) VALUES (?,?,?,?,?)";
    
    public static String CLI_QUERY_GET_LIST_DOCTORES =  "SELECT d.id              AS idD, "
                                                            + " d.ci              AS ciD,"
                                                            + " d.nombre          AS nombreD,"
                                                            + " d.apellidos       AS apellidosD,"
                                                            + " d.telefono        AS telefonoD,"
                                                            + " d.domicilio       AS domicilioD,"
                                                            + " d.id_departamento AS idDepartamentoD,"
                                                            + " d.id_especialidad AS idEspecialidadD,"
                                                            + " d.activo          AS activoD,"
                                                            + " d.sexo            AS sexoD, "
                                                            + " d.foto            AS fotoD, "
                                                            + " e.nombre          AS nombreE, "
                                                            + " dep.descripcion   as nombreDep "
                                                            + " FROM doctores d , especialidades e , departamento dep "
                                                            + " WHERE d.id_especialidad = e.id" 
                                                            + " and d.id_departamento = dep.id";
    
    public static String CLI_QUERY_GET_LIST_DOCTORES_CODIGOS =  "SELECT d.id              AS idD, "
                                                                    + " d.ci              AS ciD,"
                                                                    + " d.nombre          AS nombreD,"
                                                                    + " d.apellidos       AS apellidosD,"
                                                                    + " d.sexo            AS sexoD "
                                                                    + " FROM doctores d  "
                                                                    + " WHERE d.activo = true ";
    
    public static String CLI_QUERY_EDITAR_DOCTOR = "UPDATE doctores SET ci = ? , "
                                                                    + " nombre = ?, "
                                                                    + " apellidos = ?, "
                                                                    + " telefono = ?, "
                                                                    + " domicilio = ? ,"
                                                                    + " id_especialidad = ?, "
                                                                    + " id_departamento = ?, "
                                                                    + " sexo = ? ,"
                                                                    + " foto = ? "
                                                                    + " WHERE id = ? "; 
    
    public static String CLI_QUERY_EDITAR_PACIENTE = "UPDATE pacientes SET ci = ? , "
                                                                    + " nombre = ?, "
                                                                    + " apellidos = ?, "
                                                                    + " telefono = ?, "
                                                                    + " domicilio = ? ,"
                                                                    + " id_departamento = ?, "
                                                                    + " fecha_nacimiento = ? ,"
                                                                    + " foto = ? "
                                                                    + " WHERE id = ? "; 

    
    
    public static String CLI_QUERY_GET_CONSULTAS =  "SELECT     c.id                AS idCon, "
                                                            + " c.descripcion       AS descripcionCon,"
                                                            + " c.precio            AS precioCon,"
                                                            + " c.id_doctor         AS idDoctorCon,"
                                                            + " c.id_tipo_consulta  AS idTipoConsultaCon,"
                                                            + " c.id_especialidad   AS idEspecialidadCon,"
                                                            + " c.habilitado        AS  habilitadoCon,"
                                                            + " tc.descripcion      AS descripcionTC, "
                                                            + " e.nombre            AS nombreEsp "
                                                            + " FROM consulta c , tipo_consulta tc , especialidades e "
                                                            + " WHERE c.id_tipo_consulta = tc.id "
                                                            + "   and c.id_especialidad  = e.id ";
    
    public static String CLI_QUERY_GET_CONSULTAS_HABILITADOS =  "SELECT     c.id                AS idCon, "
                                                                        + " c.descripcion       AS descripcionCon,"
                                                                        + " c.precio            AS precioCon,"
                                                                        + " c.id_doctor         AS idDoctorCon,"
                                                                        + " c.id_tipo_consulta  AS idTipoConsultaCon,"
                                                                        + " c.id_especialidad   AS idEspecialidadCon,"
                                                                        + " c.habilitado        AS  habilitadoCon,"
                                                                        + " tc.descripcion      AS descripcionTC, "
                                                                        + " e.nombre            AS nombreEsp "
                                                                        + " FROM consulta c , tipo_consulta tc , especialidades e "
                                                                        + " WHERE c.id_tipo_consulta = tc.id "
                                                                        + "   and c.id_especialidad  = e.id "
                                                                        + "   and c.habilitado = true ";
    
    
    
    
    public static String CLI_QUERY_INSERT_AGENDA =   "INSERT INTO agenda (id_doctor, id_consultorio, dia, horadesde , horahasta) VALUES (?,?,?,?,?)";
    
    
    public static String CLI_QUERY_INSERT_MYTOKEN =  "INSERT INTO passwordresettoken (expirydate, token, user_id) VALUES (?,?,?)" ;
    
    public static String CLI_QUERY_OBT_TOKEN =  "SELECT * FROM passwordresettoken WHERE token = ? " ;
    
    
    public static String CLI_QUERY_GET_AGENDA_POR_DIAS =  "SELECT * FROM agenda WHERE ";
    
    public static String CLI_QUERY_GET_AGENDA_POR_ID_DOCTOR =  "SELECT  a.id             AS idAg, "
                                                                    + " a.dia            AS diaAg,"
                                                                    + " a.id_paciente    AS idPacienteAg,"
                                                                    + " a.horaDesde      AS horaDesdeAg,"
                                                                    + " a.horaHasta      AS horaHastaAg,"
                                                                    + " a.id_consultorio AS idConsultorioAg,"
                                                                    + " a.id_consulta    AS idConsultaAg,"
                                                                    + " a.libre          AS libreAg,"
                                                                    + " a.aceptado       AS aceptadoAg,"
                                                                    + " con.descripcion  AS descripcionCon "
                                                                    + " FROM agenda a, consultorios con  "
                                                                    + " WHERE a.id_doctor = ? "
                                                                    + "    and a.dia >= ? "
                                                                    + "    and a.id_consultorio = con.id "
                                                                    + " order by a.dia, a.horaDesde asc ";
    
    public static String CLI_QUERY_GET_AGENDA_PACIENTE =  "SELECT   a.dia            AS diaAg,"
                                                                + " a.id_paciente    AS idPacienteAg,"
                                                                + " a.id_doctor      AS idDoctorAg,"
                                                                + " a.horaDesde      AS horaDesdeAg,"
                                                                + " a.horaHasta      AS horaHastaAg,"
                                                                + " a.id_consultorio AS idConsultorioAg,"
                                                                + " a.id_consulta    AS idConsultaAg,"
                                                                + " a.libre          AS libreAg,"
                                                                + " a.aceptado       AS aceptadoAg,"
                                                                + " con.descripcion  AS descripcionCon, "
                                                                + " tc.id            AS idTC, "
                                                                + " tc.descripcion   AS descripcionTC, "
                                                                + " e.id             AS idEsp, "
                                                                + " e.nombre         AS nombreEsp "
                                                                + " FROM agenda a , consultorios con, consulta c , tipo_consulta tc , especialidades e "
                                                                + " WHERE  a.id = ? "
                                                                + "    and a.id_consultorio = con.id "
                                                                + "    and a.id_consulta = c.id  "
                                                                + "    and c.id_tipo_consulta = tc.id "
                                                                + "    and c.id_especialidad = e.id " ;
    
    public static String CLI_QUERY_GET_AGENDA_HOSTORICA_POR_ID_DOCTOR =  "SELECT    a.id             AS idAg, "
                                                                                + " a.dia            AS diaAg,"
                                                                                + " a.id_paciente    AS idPacienteAg,"
                                                                                + " a.horaDesde      AS horaDesdeAg,"
                                                                                + " a.horaHasta      AS horaHastaAg,"
                                                                                + " a.id_consultorio AS idConsultorioAg,"
                                                                                + " a.id_consulta    AS idConsultaAg,"
                                                                                + " a.id_doctor      AS idDoctorAg,"
                                                                                + " a.libre          AS libreAg,"
                                                                                + " a.aceptado       AS aceptadoAg,"
                                                                                + " con.descripcion  AS descripcionCon "
                                                                                + " FROM agenda a, consultorios con "
                                                                                + " WHERE a.id_doctor = ? "
                                                                                + "   and a.id_consultorio = con.id "
                                                                                + " order by dia desc ";
    
    
    public static String CLI_QUERY_DELETE_TURNO =  "DELETE FROM agenda WHERE id = ? ";
    
    
    public static String CLI_QUERY_GET_MI_AGENDA_POR_ID_DOCTOR =   "SELECT  a.id                 AS idAg, "
                                                                        + " a.dia                AS diaAg,"
                                                                        + " a.id_paciente        AS idPacienteAg,"
                                                                        + " a.horaDesde          AS horaDesdeAg,"
                                                                        + " a.horaHasta          AS horaHastaAg,"
                                                                        + " a.id_consultorio     AS idConsultorioAg,"
                                                                        + " a.id_consulta        AS idConsultaAg,"
                                                                        + " a.libre              AS libreAg,"
                                                                        + " a.aceptado           AS aceptadoAg,"
                                                                        + " con.descripcion      AS descripcionCon, "
                                                                        + " tc.id                AS idTC, "
                                                                        + " tc.descripcion       AS descripcionTC, "
                                                                        + " e.id                 AS idEsp, "
                                                                        + " e.nombre             AS nombreEsp, "
                                                                        + " p.id                 AS idPac, "
                                                                        + " p.ci                 AS ciPac, "
                                                                        + " p.nombre             AS nombrePac, "
                                                                        + " p.apellidos          AS apellidosPac, "
                                                                        + " p.telefono           AS telefonoPac, "
                                                                        + " p.domicilio          AS domicilioPac, "
                                                                        + " p.fecha_nacimiento   AS fechaNacimientoPac, "
                                                                        + " p.id_departamento    AS idDepartamentoPac, "
                                                                        + " p.activo             AS activoPac "
                                                                        + " FROM agenda a, consultorios con, consulta c , tipo_consulta tc , especialidades e , pacientes p"
                                                                        + " WHERE a.id_doctor = ? "
                                                                        + "    and a.dia >= ? "
                                                                        + "    and a.id_paciente IS NOT NULL "
                                                                        + "    and a.id_consulta = c.id  "
                                                                        + "    and a.id_paciente = p.id  "
                                                                        + "    and c.id_tipo_consulta = tc.id "
                                                                        + "    and a.id_consultorio = con.id "
                                                                        + "    and c.id_especialidad = e.id "
                                                                        + " order by a.dia, a.horaDesde asc ";
    
    public static String CLI_QUERY_GET_MI_AGENDA_HISTORICO_POR_ID_DOCTOR = "SELECT  a.id                 AS idAg, "
                                                                                + " a.dia                AS diaAg,"
                                                                                + " a.id_paciente        AS idPacienteAg,"
                                                                                + " a.horaDesde          AS horaDesdeAg,"
                                                                                + " a.horaHasta          AS horaHastaAg,"
                                                                                + " a.id_consultorio     AS idConsultorioAg,"
                                                                                + " a.id_consulta        AS idConsultaAg,"
                                                                                + " a.libre              AS libreAg,"
                                                                                + " a.aceptado           AS aceptadoAg,"
                                                                                + " con.descripcion      AS descripcionCon, "
                                                                                + " tc.id                AS idTC, "
                                                                                + " tc.descripcion       AS descripcionTC, "
                                                                                + " e.id                 AS idEsp, "
                                                                                + " e.nombre             AS nombreEsp, "
                                                                                + " p.id                 AS idPac, "
                                                                                + " p.ci                 AS ciPac, "
                                                                                + " p.nombre             AS nombrePac, "
                                                                                + " p.apellidos          AS apellidosPac, "
                                                                                + " p.telefono           AS telefonoPac, "
                                                                                + " p.domicilio          AS domicilioPac, "
                                                                                + " p.fecha_nacimiento   AS fechaNacimientoPac, "
                                                                                + " p.id_departamento    AS idDepartamentoPac, "
                                                                                + " p.activo             AS activoPac "
                                                                                + " FROM agenda a, consultorios con, consulta c , tipo_consulta tc , especialidades e , pacientes p"
                                                                                + " WHERE a.id_doctor = ? "
                                                                                + "    and a.id_paciente IS NOT NULL "
                                                                                + "    and a.id_consulta = c.id  "
                                                                                + "    and a.id_paciente = p.id  "
                                                                                + "    and c.id_tipo_consulta = tc.id "
                                                                                + "    and a.id_consultorio = con.id "
                                                                                + "    and c.id_especialidad = e.id "
                                                                                + " order by a.dia, a.horaDesde asc ";
    
    public static String CLI_QUERY_GET_CONSULTORIO_AGENDA_TURNO = "SELECT     a.id_consultorio     AS idConsultorio,"
                                                                        + " con.descripcion        AS descripcionCon "
                                                                        + " FROM agenda a, consultorios con"
                                                                        + " WHERE a.id = ? "
                                                                        + " and a.id_consultorio = con.id ";
    
    

}
