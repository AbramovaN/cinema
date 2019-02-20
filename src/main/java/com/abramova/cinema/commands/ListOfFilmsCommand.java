package com.abramova.cinema.commands;

import com.abramova.cinema.entities.Film;
import com.abramova.cinema.manager.Config;
import com.abramova.cinema.service.FilmService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class ListOfFilmsCommand implements Command {
    Logger logger = Logger.getLogger(ListOfFilmsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        FilmService filmService = new FilmService();
        List<Film> list = null;
        java.util.Date dateNow = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String getDate = simpleDateFormat.format(dateNow);
        try {
            if (request.getParameter("date") != null) {
                getDate = request.getParameter("date");
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar(Integer.valueOf(getDate.substring(0, 4)),
                    Integer.valueOf(getDate.substring(5, 7)) - 1, Integer.valueOf(getDate.substring(8, 10)));
            Date date = new java.sql.Date(gregorianCalendar.getTimeInMillis());
            list = filmService.selectFilmsByDate(date);
        } catch (SQLException e) {
            logger.warn("There is no films in database on this date", e);
        }
        if (list.size() == 0) {
            request.setAttribute("error", "There is no film on this date");
        }
        list.sort((Film f1, Film f2) -> f1.getFilmName().compareTo(f2.getFilmName()));
        request.setAttribute("filmsList", list);
        return Config.getInstance().getProperty(Config.LIST_OF_FILMS);
    }
}
