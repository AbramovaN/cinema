package com.abramova.cinema.dao;

import com.abramova.cinema.dao.implementation.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {
    Logger logger = Logger.getLogger(MySQLDAOFactory.class);

    private static ComboPooledDataSource poolOfConnections;
    private static MySQLDAOFactory dataSource;

    public MySQLDAOFactory() {
        poolOfConnections = new ComboPooledDataSource();
        PropertyHolder propertyHolder = PropertyHolder.getInstance();
        try {
            poolOfConnections.setDriverClass(propertyHolder.getDbDriver());
            poolOfConnections.setJdbcUrl(propertyHolder.getJdbcUrl());
            poolOfConnections.setUser(propertyHolder.getDbUserLogin());
            poolOfConnections.setPassword(propertyHolder.getDbUserPassword());
            poolOfConnections.setMinPoolSize(5);
            poolOfConnections.setAcquireIncrement(1);
            poolOfConnections.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            logger.warn("Failed to get properties for db",e);
        }
    }

    public static synchronized MySQLDAOFactory getInstance() {
        if (dataSource == null) {
            dataSource = new MySQLDAOFactory();
        }
        return dataSource;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = poolOfConnections.getConnection();
        } catch (SQLException e) {
            logger.warn("Failed to get connection", e);
        }
        return connection;
    }
}