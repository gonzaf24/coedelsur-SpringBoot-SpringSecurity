package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.Pago;

public class PagosForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Pago> listMisPagos;
    private Pago selectedPago;

    public PagosForm() {
    }

    public ArrayList<Pago> getListMisPagos() {
        return listMisPagos;
    }

    public void setListMisPagos(ArrayList<Pago> listMisPagos) {
        this.listMisPagos = listMisPagos;
    }

    public Pago getSelectedPago() {
        return selectedPago;
    }

    public void setSelectedPago(Pago selectedPago) {
        this.selectedPago = selectedPago;
    }
}
