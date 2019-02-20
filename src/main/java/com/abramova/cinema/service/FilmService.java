package com.abramova.cinema.service;

import com.abramova.cinema.dao.DAOFactory;
import com.abramova.cinema.dao.interfaces.FilmDao;
import com.abramova.cinema.entities.Film;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmService {
    private FilmDao filmDao;

    public FilmService() {
        filmDao = DAOFactory.getFilmDAO();
    }

    /**
     * Method that add film in database .
     *
     * @param film is given film to add.
     * @return true if it goes well otherwise false.
     */
    public boolean addNewFilm(Film film) throws SQLException {
        return filmDao.insert(film);
    }

    /**
     * Method that delete film in database by given id.
     *
     * @param id is id of film that you want to delete.
     * @return true if it goes well otherwise false.
     */
    public boolean deletFilm(int id) throws SQLException {
        return filmDao.deletById(id);
    }

    /**
     * Method that finds all films in database by given date.
     *
     * @param date is date that you want to get list of films on.
     * @return List of Films if empty than there is no Films in the data base on this date.
     */
    public List<Film> selectFilmsByDate(Date date) throws SQLException {
        List<Film> listOfAllFilms = filmDao.selectAll();
        List<Film> selectedFilsByDate = new ArrayList<>();
        for (Film element : listOfAllFilms) {
            if (element.getDate().equals(date)) {
                selectedFilsByDate.add(element);
            }
        }
        return selectedFilsByDate;
    }

    /**
     * Method that select film from database by given id.
     *
     * @param id is id of film that you want to get.
     * @return selected film.
     */
    public Film selectFilmByID(int id) throws SQLException {
        return filmDao.selectById(id);
    }
}
