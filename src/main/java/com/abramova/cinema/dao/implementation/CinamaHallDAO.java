package com.abramova.cinema.dao.implementation;

import com.abramova.cinema.dao.MySQLDAOFactory;
import com.abramova.cinema.dao.interfaces.CinamaHallDao;
import com.abramova.cinema.entities.CinemaHall;
import com.sun.istack.internal.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinamaHallDAO implements CinamaHallDao {
    Logger logger = Logger.getLogger(CinamaHallDAO.class);
    private final String SELECT_ALL_SQL = "SELECT * FROM `cinema_hall`";
    private final String DELETE_SQL = "DELETE FROM `cinema_hall` WHERE `id_cinema_hall` = ?";
    private final String INSERT_SQL = "INSERT INTO `cinema_hall` (`row`, `place`) VALUES (?,?)";
    private final String SELECT_SEAT_BY_ID = "SELECT * FROM `cinema_hall` WHERE `id_cinema_hall` = ?";
    private MySQLDAOFactory daoDataSource;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public CinamaHallDAO() {
        daoDataSource = MySQLDAOFactory.getInstance();
    }

    @Override
    public List<CinemaHall> selectAll() {
        List<CinemaHall> result = new ArrayList<>();
        connection = daoDataSource.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                CinemaHall cinemaHall = new CinemaHall();
                cinemaHall.setIdCinemaHall(resultSet.getInt("id_cinema_hall"));
                cinemaHall.setRow(resultSet.getInt("row"));
                cinemaHall.setPlace(resultSet.getInt("place"));
                result.add(cinemaHall);
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
    public boolean insert(CinemaHall cinemaHall) {
        try {
            connection = daoDataSource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setInt(1, cinemaHall.getRow());
            preparedStatement.setInt(2, cinemaHall.getPlace());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warning("Failed to rollback", e);
            }
            logger.warning("Can't insert new user type", e);
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
    public CinemaHall selectSeatByCinemaHallId(int cinemaHallId) {
        connection = daoDataSource.getConnection();
        CinemaHall cinemaHall = null;
        try {

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(SELECT_SEAT_BY_ID);
            preparedStatement.setInt(1, cinemaHallId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cinemaHall = new CinemaHall(
                        resultSet.getInt("id_cinema_hall"),
                        resultSet.getInt("row"),
                        resultSet.getInt("place"));
            }
            connection.close();
        } catch (SQLException e) {
            logger.warning("Can't close connection", e);
        }
        return cinemaHall;
    }
}

