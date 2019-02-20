package com.abramova.cinema.commands;

import com.abramova.cinema.entities.Ticket;
import com.abramova.cinema.manager.Config;
import com.abramova.cinema.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class HallLayoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        TicketService ticketService = new TicketService();
        HttpSession session = request.getSession();
        if (request.getParameter("price") != null && request.getParameter("idFilm") != null) {
            session.setAttribute("price", request.getParameter("price"));
            session.setAttribute("idFilm", request.getParameter("idFilm"));
        }
        List<List<Ticket>> listsTikets = ticketService.getTicketsByIdFilm(Integer.valueOf(session.getAttribute("idFilm").toString()));
        request.setAttribute("listsTikets", listsTikets);
        return Config.getInstance().getProperty(Config.HALL);
    }
}
