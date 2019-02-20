package com.abramova.cinema.dao.interfaces;

import com.abramova.cinema.entities.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDao {
    /**
     * Return all tickets that db have.
     *
     * @return List<Ticket> - result of method return.
     * @exeption provides information on a database access error.
     */
    List<Ticket> selectAll() throws SQLException;

    /**
     * @param idTicket will remove tickets .
     * @return boolean - result of method return.
     * true - if it goes well and
     * false - if removing was roll back.
     * @exeption provides information on a database access error.
     */
    boolean deletById(Integer idTicket) throws SQLException;

    /**
     * @param ticket will make another ticket in mysql table.
     * @return boolean - result of method return.
     * @exeption provides information on a database access error.
     */
    boolean insert(Ticket ticket) throws SQLException;

    /**
     * @param idFilm will show all tickets on this film.
     * @return List<Ticket> - result of method return.
     * @exeption provides information on a database access error.
     */
    List<Ticket> selectAllTicketsByFilmId(int idFilm) throws SQLException;

    /**
     * @param id will show ticket with such id
     * @return Ticket - result of method return.
     * @exeption provides information on a database access error.
     */
    Ticket selectTicketById(int id) throws SQLException;

    /**
     * @param updatedTicket will update ticket
     * @return boolean - result of method return.
     * true - if it goes well and
     * false - if removing was roll back.
     * @exeption provides information on a database access error.
     */
    boolean updateTicket(Ticket updatedTicket);

}
