package com.abramova.cinema.dao;

import com.abramova.cinema.dao.implementation.*;
import com.abramova.cinema.dao.interfaces.*;

public abstract class DAOFactory {

    public static FilmDao getFilmDAO() {
        return new FilmDAO();
    }

    public static TicketDao getTicketDAO() {
        return new TicketDAO();
    }

    public static UserDao getUserDAO() {
        return new UserDAO();
    }
}

