package com.abramova.cinema.dao.implementation;

import com.abramova.cinema.dao.MySQLDAOFactory;
import com.abramova.cinema.dao.interfaces.TicketDao;
import com.abramova.cinema.entities.CinemaHall;
import com.abramova.cinema.entities.Ticket;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements TicketDao {
    Logger logger = Logger.getLogger(TicketDAO.class);
    private final String SELECT_ALL_SQL = "SELECT * FROM `ticket`";
    private final String DELETE_SQL = "DELETE FROM `ticket` WHERE id_ticket = ?";
    private final String INSERT_SQL = "INSERT INTO `ticket` (id_ticket, id_cinema_hall,id_film, id_user, booked) VALUES (?,?,?,?,?,?)";
    private final String SELECT_ALL_TICKETS_ON_FILM = "SELECT * FROM `ticket` WHERE `id_film` = ?";
    private final String SELECT_ALL_TICKETS_BY_ID = "SELECT * FROM `ticket` WHERE `id_ticket` = ?";
    private final String UPDATE_TICKET = "UPDATE `ticket` SET `id_ticket` = ?,`id_cinema_hall` = ?,`id_film` = ?, `id_user` = ?, `booked` = ? where `id_ticket` = ?";
    private MySQLDAOFactory daoDataSource;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public TicketDAO() {
        daoDataSource = MySQLDAOFactory.getInstance();
    }

    @Override
    public List<Ticket> selectAll() {
        List<Ticket> result = new ArrayList<>();
        connection = daoDataSource.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                Ticket ticket = new Ticket(
                        resultSet.getInt("id_ticket"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_film"),
                        new CinemaHall(resultSet.getInt("id_cinema_hall")),
                        resultSet.getBoolean("booked"));
                result.add(ticket);
            }
            connection.close();
        } catch (SQLException e) {
            logger.warn("Can't close connection", e);
        }
        return result;
    }

    @Override
    public boolean deletById(Integer idTicket) {
        try {
            connection = daoDataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, idTicket);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e);
            }
            logger.warn("Can't delete ", e);
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
    public boolean insert(Ticket ticket) {
        try {
            connection = daoDataSource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setInt(1, ticket.getIdTicket());
            preparedStatement.setInt(2, ticket.getCinemaHall().getIdCinemaHall());
            preparedStatement.setInt(3, ticket.getIdFilm());
            preparedStatement.setInt(4, ticket.getIdUser());
            preparedStatement.setBoolean(5, ticket.isBooked());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e);
            }
            logger.warn("Failed to insert ", e);
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
    public List<Ticket> selectAllTicketsByFilmId(int idFilm) {
        List<Ticket> result = new ArrayList<>();
        connection = daoDataSource.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(SELECT_ALL_TICKETS_ON_FILM);
            preparedStatement.setInt(1, idFilm);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket(
                        resultSet.getInt("id_ticket"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_film"),
                        new CinemaHall(resultSet.getInt("id_cinema_hall")),
                        resultSet.getBoolean("booked"));
                result.add(ticket);
            }
            connection.close();
        } catch (SQLException e) {
            logger.warn("Can't close connection", e);
        }
        return result;
    }

    @Override
    public Ticket selectTicketById(int id) {
        connection = daoDataSource.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = connection.prepareStatement(SELECT_ALL_TICKETS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket(
                        resultSet.getInt("id_ticket"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_film"),
                        new CinemaHall(resultSet.getInt("id_cinema_hall")),
                        resultSet.getBoolean("booked"));

                return ticket;
            }
            connection.close();
        } catch (SQLException e) {
            logger.warn("Can't close connection", e);
        }
        return null;
    }

    @Override
    public boolean updateTicket(Ticket updatedTicket) {
        try {
            connection = daoDataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_TICKET);
            preparedStatement.setInt(1, updatedTicket.getIdTicket());
            preparedStatement.setInt(2, updatedTicket.getCinemaHall().getIdCinemaHall());
            preparedStatement.setLong(3, updatedTicket.getIdFilm());
            preparedStatement.setLong(4, updatedTicket.getIdUser());
            preparedStatement.setBoolean(5, updatedTicket.isBooked());
            preparedStatement.setInt(6, updatedTicket.getIdTicket());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            logger.warn("Failed to update ticket", e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.warn("Failed to rollback", e1);
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.warn("Failed to close connection", e);
            }
        }
        return true;
    }

}
