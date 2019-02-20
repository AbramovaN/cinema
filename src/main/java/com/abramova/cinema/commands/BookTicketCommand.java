package com.abramova.cinema.commands;

import com.abramova.cinema.entities.Ticket;
import com.abramova.cinema.manager.Config;
import com.abramova.cinema.service.FilmService;
import com.abramova.cinema.service.TicketService;
import com.abramova.cinema.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class BookTicketCommand implements Command {
    private static Logger logger = Logger.getLogger(BookTicketCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserService userService = new UserService();
        FilmService filmService = new FilmService();
        TicketService ticketService = new TicketService();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("error", "Please, sign in to book tickets");
            logger.info("Unregistered user try to book ticket");
            return Config.getInstance().getProperty(Config.LOGIN);
        }
        request.setAttribute("userInfo", userService.findByEmailUser(session.getAttribute("user").toString()));
        request.setAttribute("film", filmService.selectFilmByID(Integer.valueOf(session.getAttribute("idFilm").toString())));
        List<String> ticketsIdList = Arrays.asList(request.getParameterValues("ticket"));
        List<Ticket> ticketList = ticketService.getChosenTickets(ticketsIdList);
        request.setAttribute("tickets", ticketList);
        ticketService.changeTicket(ticketList, userService.findByEmailUser(session.getAttribute("user").toString()).getIdUser());
        return Config.getInstance().getProperty(Config.LIST_OF_ORDERS);
    }
}

