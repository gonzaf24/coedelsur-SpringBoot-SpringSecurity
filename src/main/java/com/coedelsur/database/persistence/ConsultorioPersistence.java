package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coedelsur.database.connections.DBManager;
import com.coedelsur.model.SelectStringValue;

public class ConsultorioPersistence extends UtilPersistence {

    public static List<SelectStringValue> obtenerConsultorios() throws Exception {
        List<SelectStringValue> salida = new ArrayList<SelectStringValue>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = DBManager.getDBConection();
            ps = conexion.prepareStatement(DBManager.CLI_QUERY_GET_CONSULTORIOS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                salida.add(new SelectStringValue(descripcion,id));
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
