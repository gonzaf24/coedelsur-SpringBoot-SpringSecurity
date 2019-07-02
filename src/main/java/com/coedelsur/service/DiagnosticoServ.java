package com.coedelsur.service;

import java.util.ArrayList;

import com.coedelsur.model.SelectCIE10;

public interface DiagnosticoServ {

    public ArrayList<SelectCIE10> obtenerPrimerCapituloCIE10() throws Exception;

    public ArrayList<SelectCIE10> obtenerSegundoCapituloCIE10(SelectCIE10 selectedPrimerCapitulo) throws Exception;

    public ArrayList<SelectCIE10> obtenerTercerCapituloCIE10(SelectCIE10 selectedSegundoCapitulo) throws Exception;

    public ArrayList<SelectCIE10> obtenerCuartoCapituloCIE10(SelectCIE10 selectedCuartoCapitulo) throws Exception;

    public ArrayList<SelectCIE10> obtenerPorTextoCIE10(String nombre) throws Exception;

    public ArrayList<SelectCIE10> obtenerPorCodigoCIE10(String codigo) throws Exception;

    public ArrayList<String> obtener10DiagnosticosLibresMasUsados() throws Exception;


}
