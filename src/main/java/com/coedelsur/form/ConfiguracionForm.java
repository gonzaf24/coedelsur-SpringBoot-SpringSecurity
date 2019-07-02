package com.coedelsur.form;

import java.io.Serializable;

public class ConfiguracionForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String algo;
    private String algoMas;

    public ConfiguracionForm() {
    }

    public String getAlgo() {
        return algo;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public String getAlgoMas() {
        return algoMas;
    }

    public void setAlgoMas(String algoMas) {
        this.algoMas = algoMas;
    }
}
