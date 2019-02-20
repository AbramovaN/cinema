package com.abramova.cinema.dao.implementation;

import com.abramova.cinema.dao.MySQLDAOFactory;
import com.abramova.cinema.dao.interfaces.UserTypeDao;
import com.abramova.cinema.entities.UserType;
import com.sun.istack.internal.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTypeDAO implements UserTypeDao {
    Logger logger = Logger.getLogger(UserTypeDAO.class);
    private final String SELECT_ALL_SQL = "SELECT * FROM `user_type`";
    private final String DELETE_SQL = "DELETE FROM `user_type` WHERE `id_user_type` = ?";
    private final String INSERT_SQL = "INSERT INTO `user_type` (`user_type`)VALUES (?)";
    private MySQLDAOFactory daoDataSource;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private UserType userType;

    public UserTypeDAO() {
        daoDataSource = MySQLDAOFactory.getInstance();
    }

    @Override
    public List<UserType> selectAll() {
        List<UserType> result = new ArrayList<>();
        connection = daoDataSource.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                userType.setIdUserType(resultSet.getInt("id_user_type"));
                userType.setUserType(resultSet.getString("user_type"));
                result.add(userType);
            }
            connection.close();
        } catch (SQLException e) {
            logger.warning("Can't close connection ", e);
        }
        return result;
    }

    @Override
    public boolean deletById(Integer id) {
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warning("Failed to rollback", e);
            }
            logger.warning("Faild to delete by this id", e);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warning("Can't close connection ", e);
            }
        }
        return true;
    }

    @Override
    public void insert(UserType userType) {
        try {
            connection = daoDataSource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, userType.getUserType());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warning("Failed to rollback", e);
            }
            logger.warning("Can't insert new user type", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warning("Can't close connection ", e);
            }
        }

    }
}
