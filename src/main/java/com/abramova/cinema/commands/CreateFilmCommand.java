package com.abramova.cinema.commands;

import com.abramova.cinema.entities.Film;
import com.abramova.cinema.manager.Config;
import com.abramova.cinema.service.FilmService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;

public class CreateFilmCommand implements Command {
    private static Logger logger = Logger.getLogger(CreateFilmCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        FilmService filmService = new FilmService();
        Film film = new Film();
        film.setFilmName(request.getParameter("name"));
        film.setGenre(request.getParameter("genre"));
        film.setDescription(request.getParameter("description"));
        try {
            film.setPrice(Float.parseFloat(request.getParameter("price")));
            String getDate = request.getParameter("date");
            GregorianCalendar gregorianCalendar = new GregorianCalendar(Integer.valueOf(getDate.substring(0, 4)),
                    Integer.valueOf(getDate.substring(5, 7)) - 1, Integer.valueOf(getDate.substring(8, 10)));
            Date date = new java.sql.Date(gregorianCalendar.getTimeInMillis());
            film.setDate(date);
            Time time = Time.valueOf(request.getParameter("time") + ":" + "00");
            film.setTime(time);
        } catch (NullPointerException | IllegalArgumentException e) {
            logger.warn("NullPointerException | IllegalArgumentException", e);
        }
        if (!filmService.addNewFilm(film)) {
            logger.info("New film successfully added");
            return "jsp/createFilm.jsp";
        }
        return Config.getInstance().getProperty(Config.ADMIN);
    }
}
