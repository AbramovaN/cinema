package com.abramova.cinema.dao.implementation;

import com.abramova.cinema.dao.MySQLDAOFactory;
import com.abramova.cinema.dao.interfaces.UserDao;
import com.abramova.cinema.entities.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDao {
    Logger logger = Logger.getLogger(UserDAO.class);
    private final String SELECT_ALL_SQL = "SELECT * FROM `user`";
    private final String DELETE_SQL = "DELETE FROM `user` WHERE id_user = ?";
    private final String INSERT_SQL = "INSERT INTO `user` (surname, name, phone_number, email, password, id_user_type, operator_code) VALUES (?,?,?,?,?,?,?)";
    private final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT * FROM `user` WHERE `email` = ? AND `password` = ? ";
    private final String SELECT_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ? ";
    private final String SELECT_IDTYPE_BY_EMAIL = "SELECT `id_user_type` FROM user WHERE `email` = ?";
    private MySQLDAOFactory daoDataSource;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDAO() {
        daoDataSource = MySQLDAOFactory.getInstance();
    }

    @Override
    public List<User> selectAll() {
        List<User> result = new ArrayList<>();
        connection = daoDataSource.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("id_user"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setOperatorCode(resultSet.getInt("operator_code"));
                user.setPhoneNumber(resultSet.getInt("phone_number"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIdUserType(resultSet.getInt("id_user_type"));
                result.add(user);
            }
            connection.close();
        } catch (SQLException e) {
            logger.warn("Can't close connection ", e);
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
                logger.warn("Failed to rollback", e);
            }
            logger.warn("Can't delete this user", e);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn("Can't close connection", e);
            }
        }
        return true;
    }

    @Override
    public boolean insert(User user) {
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getIdUserType());
            preparedStatement.setInt(7, user.getOperatorCode());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e);
            }
            logger.warn("Failed to insert user", e);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn("Can't close connection", e);
            }
        }
        return true;
    }

    @Override
    public boolean findByEmailAndPassword(String email, String password) {
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            statement = connection.createStatement();
            return resultSet.next();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e);
            }
            logger.warn("There is no user with such email and password", e);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn("Can't close connection", e);
            }
        }
    }

    @Override
    public boolean findByEmail(String email) {
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e);
            }
            logger.warn("There is no user with such email", e);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn("Can't close connection", e);
            }
        }
    }

    @Override
    public int findIdTypeByEmail(String email) {
        int i = 0;
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(SELECT_IDTYPE_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                i = resultSet.getInt("id_user_type");
            }
            return i;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e);
            }
            logger.warn("Fail to find id by this email", e);
            return i;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn("Can't close connection", e);
            }
        }
    }
    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setIdUser(resultSet.getInt("id_user"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setOperatorCode(resultSet.getInt("operator_code"));
                user.setPhoneNumber(resultSet.getInt("phone_number"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIdUserType(resultSet.getInt("id_user_type"));
            }
            connection.close();
        } catch (SQLException e) {
            logger.warn("Can't close connection", e);
        }
        return user;
    }
}
