package com.abramova.cinema.manager;

import java.util.ResourceBundle;

public class Config {

    private static Config config;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";
    public static final String HOME = "HOME";
    public static final String LOGIN = "LOGIN";
    public static final String REGISTRATION = "REGISTRATION";
    public static final String LIST_OF_FILMS = "LIST_OF_FILMS";
    public static final String ADMIN = "ADMIN";
    public static final String DELETE_FILM = "DELETE_FILM";
    public static final String HALL = "HALL";
    public static final String LIST_OF_ORDERS = "LIST_OF_ORDERS";

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
            config.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return config;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
