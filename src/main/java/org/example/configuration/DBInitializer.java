package org.example.configuration;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DBInitializer {

    final static String URL;
    final static String USER;
    final static String PASSWORD;
    final static int POOL_SIZE;
    final static String DRIVER_NAME;

    private DBInitializer(){
        super();
    }

    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
            DRIVER_NAME = resourceBundle.getString("driverClassName");
            URL = resourceBundle.getString("url");
            USER = resourceBundle.getString("username");
            PASSWORD = resourceBundle.getString("pass");
            POOL_SIZE = Integer.parseInt(resourceBundle.getString("poolSize"));
        } catch (NumberFormatException | MissingResourceException e) {
            throw new RuntimeException("Properties file error", e);
        }
    }
}
