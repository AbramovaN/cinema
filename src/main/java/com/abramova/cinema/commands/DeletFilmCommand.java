package com.abramova.cinema.commands;

import com.abramova.cinema.manager.Config;
import com.abramova.cinema.service.FilmService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeletFilmCommand implements Command {
    Logger logger = Logger.getLogger(DeletFilmCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        FilmService filmService = new FilmService();
        try {
            if (!filmService.deletFilm(Integer.valueOf(request.getParameter("idFilm")))) {
                request.setAttribute("error", "Wrong film id");
            }
            logger.info("Film was successfully deleted");
        } catch (SQLException | NumberFormatException e) {
            logger.warn("Wrong film id", e);
        }
        return Config.getInstance().getProperty(Config.DELETE_FILM);
    }
}
