package com.coedelsur.form;

import java.io.Serializable;

import com.coedelsur.model.Doctor;

public class DoctorForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private Doctor doctor;

    public DoctorForm() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
