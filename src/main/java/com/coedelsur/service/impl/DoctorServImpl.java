package com.coedelsur.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.DoctorPersistence;
import com.coedelsur.database.persistence.UsuarioPersistence;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.model.Usuario;
import com.coedelsur.service.DoctorServ;
import com.coedelsur.util.ConstantesUY;

@Component
public class DoctorServImpl implements DoctorServ {

    @Override
    public Doctor obtenerDoctor(Integer id) throws Exception {
        try {
            return DoctorPersistence.obtenerDoctor(id);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obetener el paciente.");
        }
    }

    @Override
    public boolean crearUsuarioDoctor(Doctor doctor, Usuario usuario) throws Exception {
        try {
            usuario.setRole(ConstantesUY.DOCTOR_ROLE);
            usuario.setTipo(ConstantesUY.DOCTOR_TYPE);
            usuario.setHabilitado(true);
            //hay que agregar validacion de si existe usurario
            Long id = UsuarioPersistence.registrarUsuario(usuario);
            doctor.setId(id.intValue());
            DoctorPersistence.registrarDoctor(doctor);
            return true;
        } catch (Exception e) {
            throw new Exception("Hubo un problema al registrar el doctor" + e.getMessage());
        }
    }

    @Override
    public ArrayList<Doctor> obtenerListaDoctores() throws Exception {
        return DoctorPersistence.obtenerListaDoctores();
        
    }
    
    @Override
    public ArrayList<SelectStringValue> obtenerListaDoctoresCodigos() throws Exception {
        return DoctorPersistence.obtenerListaDoctoresCodigos();
    }

    @Override
    public void editarDatosDoctor(Doctor doctorSelectedEdicion) throws Exception {
        try {
            DoctorPersistence.editarDoctor(doctorSelectedEdicion);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al guardar la edicion el doctor" + e.getMessage());
        }
        
    }

    
}
