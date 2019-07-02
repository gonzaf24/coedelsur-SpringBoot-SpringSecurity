package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.SelectCIE10;

public class DiagnosticoForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<SelectCIE10> primerCapituloList = new ArrayList<SelectCIE10>();
    private ArrayList<SelectCIE10> segundoCapituloList = new ArrayList<SelectCIE10>();
    private ArrayList<SelectCIE10> tercerCapituloList = new ArrayList<SelectCIE10>();
    private ArrayList<SelectCIE10> cuartoCapituloList = new ArrayList<SelectCIE10>();
    private ArrayList<SelectCIE10> busquedaTextoList = new ArrayList<SelectCIE10>();
    private ArrayList<SelectCIE10> busquedaCodigoList = new ArrayList<SelectCIE10>();
    private ArrayList<String> diezMasUsadosTextoLibreList = new ArrayList<String>();
    private String selectedTxtLibreMasUsado;
    private String busquedaTexto;
    private String busquedaCodigo;
    private String textoLibre;
    private String selectedTipoDiagnostico;
    private SelectCIE10 selectedPrimerCapitulo;
    private SelectCIE10 selectedSegundoCapitulo;
    private SelectCIE10 selectedTercerCapitulo;
    private SelectCIE10 selectedCIE10 = new SelectCIE10();
    private boolean esTextoLibre;
    private boolean esCIE10;

    public DiagnosticoForm() {
    }

    public ArrayList<SelectCIE10> getPrimerCapituloList() {
        return primerCapituloList;
    }

    public void setPrimerCapituloList(ArrayList<SelectCIE10> primerCapituloList) {
        this.primerCapituloList = primerCapituloList;
    }

    public ArrayList<SelectCIE10> getSegundoCapituloList() {
        return segundoCapituloList;
    }

    public void setSegundoCapituloList(ArrayList<SelectCIE10> segundoCapituloList) {
        this.segundoCapituloList = segundoCapituloList;
    }

    public ArrayList<SelectCIE10> getTercerCapituloList() {
        return tercerCapituloList;
    }

    public void setTercerCapituloList(ArrayList<SelectCIE10> tercerCapituloList) {
        this.tercerCapituloList = tercerCapituloList;
    }

    public ArrayList<SelectCIE10> getCuartoCapituloList() {
        return cuartoCapituloList;
    }

    public void setCuartoCapituloList(ArrayList<SelectCIE10> cuartoCapituloList) {
        this.cuartoCapituloList = cuartoCapituloList;
    }

    public ArrayList<SelectCIE10> getBusquedaTextoList() {
        return busquedaTextoList;
    }

    public void setBusquedaTextoList(ArrayList<SelectCIE10> busquedaTextoList) {
        this.busquedaTextoList = busquedaTextoList;
    }

    public ArrayList<SelectCIE10> getBusquedaCodigoList() {
        return busquedaCodigoList;
    }

    public void setBusquedaCodigoList(ArrayList<SelectCIE10> busquedaCodigoList) {
        this.busquedaCodigoList = busquedaCodigoList;
    }

    public SelectCIE10 getSelectedCIE10() {
        return selectedCIE10;
    }

    public void setSelectedCIE10(SelectCIE10 selectedCIE10) {
        this.selectedCIE10 = selectedCIE10;
    }

    public String getBusquedaTexto() {
        return busquedaTexto;
    }

    public void setBusquedaTexto(String busquedaTexto) {
        this.busquedaTexto = busquedaTexto;
    }

    public String getBusquedaCodigo() {
        return busquedaCodigo;
    }

    public void setBusquedaCodigo(String busquedaCodigo) {
        this.busquedaCodigo = busquedaCodigo;
    }

    public SelectCIE10 getSelectedPrimerCapitulo() {
        return selectedPrimerCapitulo;
    }

    public void setSelectedPrimerCapitulo(SelectCIE10 selectedPrimerCapitulo) {
        this.selectedPrimerCapitulo = selectedPrimerCapitulo;
    }

    public SelectCIE10 getSelectedSegundoCapitulo() {
        return selectedSegundoCapitulo;
    }

    public void setSelectedSegundoCapitulo(SelectCIE10 selectedSegundoCapitulo) {
        this.selectedSegundoCapitulo = selectedSegundoCapitulo;
    }

    public SelectCIE10 getSelectedTercerCapitulo() {
        return selectedTercerCapitulo;
    }

    public void setSelectedTercerCapitulo(SelectCIE10 selectedTercerCapitulo) {
        this.selectedTercerCapitulo = selectedTercerCapitulo;
    }

    public String getSelectedTipoDiagnostico() {
        return selectedTipoDiagnostico;
    }

    public void setSelectedTipoDiagnostico(String selectedTipoDiagnostico) {
        this.selectedTipoDiagnostico = selectedTipoDiagnostico;
    }

    public boolean isEsTextoLibre() {
        return esTextoLibre;
    }

    public void setEsTextoLibre(boolean esTextoLibre) {
        this.esTextoLibre = esTextoLibre;
    }

    public boolean isEsCIE10() {
        return esCIE10;
    }

    public void setEsCIE10(boolean esCIE10) {
        this.esCIE10 = esCIE10;
    }

    public String getTextoLibre() {
        return textoLibre;
    }

    public void setTextoLibre(String textoLibre) {
        this.textoLibre = textoLibre;
    }

    public ArrayList<String> getDiezMasUsadosTextoLibreList() {
        return diezMasUsadosTextoLibreList;
    }

    public void setDiezMasUsadosTextoLibreList(ArrayList<String> diezMasUsadosTextoLibreList) {
        this.diezMasUsadosTextoLibreList = diezMasUsadosTextoLibreList;
    }

    public String getSelectedTxtLibreMasUsado() {
        return selectedTxtLibreMasUsado;
    }

    public void setSelectedTxtLibreMasUsado(String selectedTxtLibreMasUsado) {
        this.selectedTxtLibreMasUsado = selectedTxtLibreMasUsado;
    }
}
