package com.coedelsur.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.DiagnosticoPersistence;
import com.coedelsur.model.SelectCIE10;
import com.coedelsur.service.DiagnosticoServ;

@Component
public class DiagnosticoServImpl implements DiagnosticoServ {

    @Override
    public ArrayList<SelectCIE10> obtenerPrimerCapituloCIE10() throws Exception {
        try {
            return DiagnosticoPersistence.obtenerPrimerCapituloCIE10();
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener CIE10. " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<SelectCIE10> obtenerSegundoCapituloCIE10(SelectCIE10 selectedPrimerCapitulo) throws Exception {
        try {
            return DiagnosticoPersistence.obtenerSegundoCapituloCIE10(selectedPrimerCapitulo);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener CIE10. " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<SelectCIE10> obtenerTercerCapituloCIE10(SelectCIE10 selectedSegundoCapitulo) throws Exception {
        try {
            return DiagnosticoPersistence.obtenerTercerCapituloCIE10(selectedSegundoCapitulo);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener CIE10. " + e.getMessage());
        }
    }
    
    
    @Override
    public ArrayList<SelectCIE10> obtenerCuartoCapituloCIE10(SelectCIE10 selectedCuartoCapitulo) throws Exception {
        try {
            return DiagnosticoPersistence.obtenerCuartoCapituloCIE10(selectedCuartoCapitulo);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener CIE10. " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<SelectCIE10> obtenerPorTextoCIE10(String nombre) throws Exception {
        try {
            return DiagnosticoPersistence.obtenerPorTextoCIE10(nombre);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener CIE10. " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<SelectCIE10> obtenerPorCodigoCIE10(String codigo) throws Exception {
        try {
            return DiagnosticoPersistence.obtenerPorCodigoCIE10(codigo);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener CIE10. " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<String> obtener10DiagnosticosLibresMasUsados() throws Exception {
        try {
            return DiagnosticoPersistence.obtener10DiagnosticosLibresMasUsados();
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener mas usados. " + e.getMessage());
        }
    }
   
}
