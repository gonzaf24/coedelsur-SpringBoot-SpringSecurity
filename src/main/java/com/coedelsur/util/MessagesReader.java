package com.coedelsur.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessagesReader {

    private static MessagesReader miMessagesReader;
    private static Properties properties;

    private MessagesReader() {
        try {
            properties = new Properties();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("ValidationMessages.properties");
            properties.load(input);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static String getMessage(String key) {
        if (miMessagesReader == null) {
            miMessagesReader = new MessagesReader();
        }
        return properties.getProperty(key);
    }
}
