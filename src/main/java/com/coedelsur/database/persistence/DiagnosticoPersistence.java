package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.coedelsur.database.connections.ManagerDB;
import com.coedelsur.database.connections.Querys;
import com.coedelsur.model.SelectCIE10;

public class DiagnosticoPersistence extends UtilPersistence {

    public static ArrayList<SelectCIE10> obtenerPrimerCapituloCIE10() throws Exception {
        ArrayList<SelectCIE10> salida = new ArrayList<SelectCIE10>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_PRIMER_CAPITULO_DIAGNOSTICO);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id10 = rs.getString("id10");
                String dec10 = rs.getString("dec10");
                String grp10 = rs.getString("grp10");
                String id10Salida = id10.replace("|", "");
                salida.add(new SelectCIE10(id10Salida,dec10,grp10));
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
    
    public static ArrayList<SelectCIE10> obtenerSegundoCapituloCIE10(SelectCIE10 selectPrimerCapitulo) throws Exception {
        ArrayList<SelectCIE10> salida = new ArrayList<SelectCIE10>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_SEGUNDO_CAPITULO_DIAGNOSTICO);
            ps.setString(1, "|"+selectPrimerCapitulo.getId10()+"%");
            ps.setString(2, selectPrimerCapitulo.getGrp10());
            rs = ps.executeQuery();
            while (rs.next()) {
                String id10 = rs.getString("id10");
                String dec10 = rs.getString("dec10");
                String grp10 = rs.getString("grp10");
                String id10Salida = id10.replace("|", "");
                salida.add(new SelectCIE10(id10Salida,dec10,grp10));
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
    
    public static ArrayList<SelectCIE10> obtenerTercerCapituloCIE10(SelectCIE10 selectSegundoCapitulo) throws Exception {
        ArrayList<SelectCIE10> salida = new ArrayList<SelectCIE10>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_TERCER_CAPITULO_DIAGNOSTICO);
            ps.setString(1, "|"+selectSegundoCapitulo.getId10());
            rs = ps.executeQuery();
            while (rs.next()) {
                String id10 = rs.getString("id10");
                String dec10 = rs.getString("dec10");
                String grp10 = rs.getString("grp10");
                String id10Salida = id10.replace("|", "");
                salida.add(new SelectCIE10(id10Salida,dec10,grp10));
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
    
    public static ArrayList<SelectCIE10> obtenerCuartoCapituloCIE10(SelectCIE10 selectTercerCapitulo) throws Exception {
        ArrayList<SelectCIE10> salida = new ArrayList<SelectCIE10>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_CUARTO_CAPITULO_DIAGNOSTICO);
            ps.setString(1, selectTercerCapitulo.getId10()+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String id10 = rs.getString("id10");
                String dec10 = rs.getString("dec10");
                String grp10 = rs.getString("grp10");
                String id10Salida = id10.replace("|", "");
                salida.add(new SelectCIE10(id10Salida,dec10,grp10));
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
    
    public static ArrayList<SelectCIE10> obtenerPorTextoCIE10(String nombre) throws Exception {
        ArrayList<SelectCIE10> salida = new ArrayList<SelectCIE10>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_DIAGNOSTICO_POR_TEXTO);
            
            ps.setString(1, filtroBusquedaPorTexto(nombre));
            rs = ps.executeQuery();
            while (rs.next()) {
                String id10 = rs.getString("id10");
                String dec10 = rs.getString("dec10");
                String grp10 = rs.getString("grp10");
                String id10Salida = id10.replace("|", "");
                salida.add(new SelectCIE10(id10Salida,dec10,grp10));
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

    private static String filtroBusquedaPorTexto(String originalString) {
        final String DELIMITADOR = " ";
        String palabraSalida = "%";
        String[] todasPalabras = originalString.split(DELIMITADOR);
        for(int i=0; i < todasPalabras.length; i++) {
            palabraSalida = palabraSalida + todasPalabras[i] + "%" ;
        }
        return palabraSalida;
    }
    
    public static ArrayList<SelectCIE10> obtenerPorCodigoCIE10(String codigo) throws Exception {
        ArrayList<SelectCIE10> salida = new ArrayList<SelectCIE10>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_DIAGNOSTICO_POR_CODIGO);
            ps.setString(1, codigo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String id10 = rs.getString("id10");
                String dec10 = rs.getString("dec10");
                String grp10 = rs.getString("grp10");
                String id10Salida = id10.replace("|", "");
                salida.add(new SelectCIE10(id10Salida,dec10,grp10));
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
    
    
    public static ArrayList<String> obtener10DiagnosticosLibresMasUsados() throws Exception {
        ArrayList<String> salida = new ArrayList<String>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = ManagerDB.getDBConection();
            ps = conexion.prepareStatement(Querys.CLI_QUERY_GET_DIAGNOSTICO_LIBRE_MAS_USADO);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dec10 = rs.getString("valor");
                salida.add(dec10);
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
