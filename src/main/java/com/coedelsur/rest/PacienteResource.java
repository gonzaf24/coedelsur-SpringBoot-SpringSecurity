package com.coedelsur.rest;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coedelsur.model.Paciente;
import com.coedelsur.model.Usuario;
import com.coedelsur.rest.model.PacienteRest;
import com.coedelsur.service.UsuarioServ;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/paciente")
@Api(tags = "paciente")
public class PacienteResource {
	
	final static Logger logger = Logger.getLogger(PacienteResource.class);

	@Inject
	UsuarioServ usuarioServ;

	public PacienteResource() {
	}

	@PostMapping
	@ApiOperation(value = "Crear Paciente", notes = "Servicio para registrar paciente")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Paciente creado correctamente"),@ApiResponse(code = 400, message = "Solicitud Inválida")})
	public ResponseEntity<Boolean> crearPaciente(@RequestBody PacienteRest pacienteRest) {
		logger.info( "Invocando servicio /api/paciente - createCliente : param = " + pacienteRest.getEmail() );
		Usuario user = new Usuario();
		user.setHabilitado(Boolean.TRUE);
		user.setRole("ROLE_PACIENTE");
		user.setTipo("PACIENTE");
		user.setUsername(pacienteRest.getEmail());
		Paciente paciente = new Paciente();
		paciente.setEmail(pacienteRest.getEmail());
		paciente.setCedulaIdentidad(pacienteRest.getCedulaIdentidad());
		paciente.setNombre(pacienteRest.getNombre());
		paciente.setApellidos(pacienteRest.getApellidos());
		paciente.setTelefono(pacienteRest.getTelefono());
		paciente.setIdDepartamento(pacienteRest.getIdDepartamento());
		paciente.setDomicilio(pacienteRest.getDomicilio());
		paciente.setFechaNacimiento(pacienteRest.getFechaNacimiento());
		try {
			if (!usuarioServ.existeEmail(paciente.getEmail())) {
				usuarioServ.registrarUsuario(user, paciente);
				logger.info( "Creado correctamente " );
				return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
			}else{
				logger.info( "El email ya se encuentra registrado :  " + paciente.getEmail() );
				return new ResponseEntity<>(Boolean.FALSE, HttpStatus.CONFLICT);
			}
			
		} catch (Exception e) {
			logger.info( "Hubo errror al crear paciente :  " + e.getMessage() );
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{email}")
	@ApiOperation(value = "Consultar Email", notes = "Servicio para saber si un email se encuentra ya registrado")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Email se encuentra registrado"),@ApiResponse(code = 404, message = "Email no se encuentra registrado")})
	public ResponseEntity<Boolean> existeEmail(@PathVariable("email") String email) {
		logger.info( "Invocando servicio /api/paciente - existeEmail : param = " + email );
		Boolean salida;
		try {
			salida = usuarioServ.existeEmail(email);
			if (!salida) {
				logger.info( "No se encuentra registrado el email :  " + email );
				return new ResponseEntity<>(salida, HttpStatus.NOT_FOUND);
			} else {
				logger.info( "El email : " + email + " - se encuentra registrado OK ! " );
				return new ResponseEntity<>(salida, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info( "Hubo errror el siguiente error :  " + e.getMessage() );
		}
		return null;
	}

	
}
