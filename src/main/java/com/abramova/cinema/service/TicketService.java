package com.abramova.cinema.service;

import com.abramova.cinema.dao.DAOFactory;
import com.abramova.cinema.dao.implementation.CinamaHallDAO;
import com.abramova.cinema.dao.interfaces.TicketDao;
import com.abramova.cinema.entities.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private TicketDao ticketDAO;
    CinamaHallDAO cinamaHallDAO = new CinamaHallDAO();

    public TicketService() {
        ticketDAO = DAOFactory.getTicketDAO();
    }

    /**
     * Method that marks tickets as booked and add user who bought these tickets.
     *
     * @param ticketList Tickets that user have choose
     * @param idUser     id User who is making an order
     * @return true if everything ok and false if some mistakes appeared
     */
    public boolean changeTicket(List<Ticket> ticketList, int idUser) {
        for (Ticket el : ticketList) {
            el.setBooked(true);
            el.setIdUser(idUser);
            return ticketDAO.updateTicket(el);
        }
        return false;
    }

    /**
     * Method that return list of selected tickets by they id
     *
     * @param ticketsIdList id of Tickets that user have choose
     * @return List<Ticket> list of chosen tickets
     */
    public List<Ticket> getChosenTickets(List<String> ticketsIdList) throws SQLException {
        List<Ticket> ticketList = new ArrayList<>();
        for (String el : ticketsIdList) {
            Ticket ticket = ticketDAO.selectTicketById(Integer.valueOf(el));
            ticket.setCinemaHall(cinamaHallDAO.selectSeatByCinemaHallId(ticket.getCinemaHall().getIdCinemaHall()));
            ticketList.add(ticket);
        }
        return ticketList;
    }

    /**
     * Method that finds all Tickets that correspond to this Film.
     *
     * @param idFilm id of chosen film
     * @return List of Tickets' lists if empty than there is no Tickets that correspond to this Film.
     */
    public List<List<Ticket>> getTicketsByIdFilm(int idFilm) throws SQLException {
        List<Ticket> ticketList = ticketDAO.selectAllTicketsByFilmId(idFilm);
        for (Ticket el : ticketList) {
            el.setCinemaHall(cinamaHallDAO.selectSeatByCinemaHallId(el.getCinemaHall().getIdCinemaHall()));
        }
        List<List<Ticket>> listsTikets = new ArrayList<>();
        listsTikets.add(ticketList.subList(0, 5));
        listsTikets.add(ticketList.subList(5, 10));
        listsTikets.add(ticketList.subList(5, 10));
        listsTikets.add(ticketList.subList(10, 15));
        listsTikets.add(ticketList.subList(15, 20));
        return listsTikets;
    }
}
