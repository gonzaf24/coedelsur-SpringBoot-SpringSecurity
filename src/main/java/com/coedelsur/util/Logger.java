package com.coedelsur.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Logger {

    public enum Level {
        DEBUG, ERROR, INFO, WARNING
    }

    public static Logger getLogger() {
        return new Logger();
    }
   
    private Logger() {
    }

    public void debug(Object... obj) {
        System.out.println(getLogginPattern(Level.DEBUG) + getLogMessage(obj));
    }

    public void error(Object... obj) {
        System.out.println(getLogginPattern(Level.ERROR) + getLogMessage(obj));
    }

    private String getLogginPattern(Level level) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        GregorianCalendar fecha = new GregorianCalendar();
        StringBuilder builder = new StringBuilder();
        builder.append(fecha.get(Calendar.DAY_OF_MONTH)).append('/');
        builder.append(fecha.get(Calendar.MONTH)).append('/');
        builder.append(fecha.get(Calendar.YEAR)).append(' ');
        builder.append(fecha.get(Calendar.HOUR_OF_DAY)).append(':');
        builder.append(fecha.get(Calendar.MINUTE)).append(':');
        builder.append(fecha.get(Calendar.SECOND)).append(',');
        builder.append(fecha.get(Calendar.MILLISECOND)).append(' ');
        builder.append(level).append(' ');
        builder.append(stackTraceElement.getClassName()).append('.');
        builder.append(stackTraceElement.getMethodName()).append('[').append(stackTraceElement.getLineNumber()).append(']').append(' ');
        return builder.toString();
    }

    private String getLogMessage(Object... obj) {
        StringBuilder builder = new StringBuilder();
        if (obj != null) {
            for (int i = 0; i < obj.length; i++) {
                builder.append('{');
                builder.append(getLogMessage(obj[i]));
                builder.append('}');
                if (i + 1 < obj.length) {
                    builder.append(' ');
                }
            }
        }
        return builder.toString();
    }

    private String getLogMessage(Object obj) {
        StringBuilder builder = new StringBuilder();
        if (obj == null) {
            builder.append("null");
        } else {
            if (Collection.class.isAssignableFrom(obj.getClass())) {
                builder.append('[');
                for (Iterator<?> iter = ((Collection<?>) obj).iterator(); iter.hasNext();) {
                    builder.append(getLogMessage(iter.next()));
                    if (iter.hasNext()) {
                        builder.append(',');
                    }
                }
                builder.append(']');
            } else if (Exception.class.isAssignableFrom(obj.getClass())) {
                StringWriter writter = new StringWriter();
                ((Exception) obj).printStackTrace(new PrintWriter(writter));
                builder.append(writter.getBuffer().toString());
            } else {
                builder.append(obj.toString());
            }
        }
        return builder.toString();
    }

    public void info(Object... obj) {
        System.out.println(getLogginPattern(Level.INFO) + getLogMessage(obj));
    }
}
