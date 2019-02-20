package com.abramova.cinema.dao.implementation;

import com.abramova.cinema.dao.MySQLDAOFactory;
import com.abramova.cinema.dao.interfaces.FilmDao;
import com.abramova.cinema.entities.Film;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements FilmDao {
    Logger logger = Logger.getLogger(FilmDAO.class);
    private final String SELECT_ALL_SQL = "SELECT * FROM `film`";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM `film` WHERE id_film = ?";
    private final String FIND_BY_ID = "SELECT * FROM `film` WHERE id_film = ?";
    private final String DELETE_SQL = "DELETE FROM `film` WHERE id_film = ?";
    private final String INSERT_SQL = "INSERT INTO `film` (film_name, genre, description, price, date, time) VALUES (?,?,?,?,?,?)";
    private MySQLDAOFactory daoDataSource;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public FilmDAO() {
        daoDataSource = MySQLDAOFactory.getInstance();
    }

    @Override
    public List<Film> selectAll() {
        List<Film> result = new ArrayList<>();
        connection = daoDataSource.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                Film film = new Film();
                film.setIdFilm(resultSet.getInt("id_film"));
                film.setFilmName(resultSet.getString("film_name"));
                film.setGenre(resultSet.getString("genre"));
                film.setDescription(resultSet.getString("description"));
                film.setDate(resultSet.getDate("date"));
                film.setTime(resultSet.getTime("time"));
                film.setPrice(resultSet.getFloat("price"));
                result.add(film);
            }
            connection.close();
        } catch (SQLException e) {
            logger.warn("Can't close connection", e);
        }
        return result;
    }

    @Override
    public boolean findById(Integer idFlm) {
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, idFlm);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            logger.warn("Failed to find film by id", e);
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
    public boolean deletById(Integer idFilm) {
        try {
            if (findById(idFilm)) {
                connection = daoDataSource.getConnection();
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(DELETE_SQL);
                preparedStatement.setInt(1, idFilm);
                preparedStatement.executeUpdate();
                connection.commit();
            } else return false;
        } catch (SQLException e) {
            logger.warn("Can't delete film with such id", e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e);
            }
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
    public boolean insert(Film film) {
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, film.getFilmName());
            preparedStatement.setString(2, film.getGenre());
            preparedStatement.setString(3, film.getDescription());
            preparedStatement.setDouble(4, film.getPrice());
            preparedStatement.setDate(5, (Date) film.getDate());
            preparedStatement.setTime(6, film.getTime());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e);
            }
            logger.warn("Can't insert film", e);
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
    public Film selectById(int id) {
        Film film = new Film();
        connection = daoDataSource.getConnection();
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                film.setIdFilm(resultSet.getInt("id_film"));
                film.setFilmName(resultSet.getString("film_name"));
                film.setGenre(resultSet.getString("genre"));
                film.setDescription(resultSet.getString("description"));
                film.setDate(resultSet.getDate("date"));
                film.setTime(resultSet.getTime("time"));
                film.setPrice(resultSet.getFloat("price"));
            }
            connection.close();
        } catch (SQLException e) {
            logger.warn("Can't close connection", e);
        }
        return film;
    }
}
