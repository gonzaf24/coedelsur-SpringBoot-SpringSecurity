package com.coedelsur.database.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class UtilPersistence {

    public static void closeCon(Connection conexion, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public static java.util.Date sumarRestarDiasFecha(java.util.Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public static java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }

    public static java.sql.Timestamp convertFromJAVADateToSQLTimeStamp(java.util.Date javaDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(javaDate);
        return new java.sql.Timestamp(cal.getTimeInMillis());
    }
    
    public static java.sql.Timestamp convertFromJAVADateToSQLTimeStampAUX(java.util.Date javaDateDAY, java.util.Date javaDateTIME) {
        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.setTime(javaDateDAY);
        int year = calendarInstance.get(Calendar.YEAR);
        int month = calendarInstance.get(Calendar.MONTH);
        int day = calendarInstance.get(Calendar.DAY_OF_MONTH);
        Calendar calendarBD = Calendar.getInstance();
        calendarBD.setTime(javaDateTIME);
        calendarBD.set(year, month, day);
        return new java.sql.Timestamp(calendarBD.getTimeInMillis());
    }
}
