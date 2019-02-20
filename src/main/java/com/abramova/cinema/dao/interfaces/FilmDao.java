package com.abramova.cinema.dao.interfaces;

import com.abramova.cinema.entities.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmDao {
    /**
     * @return List<Film> - result of method return.
     * List of all films that  mysql table keep.
     * @exeption provides information on a database access error.
     */
    List<Film> selectAll() throws SQLException;

    /**
     * @param id will remove film .
     * @return boolean - result of method return.
     * true - if it goes well and
     * false - if removing was roll back.
     * @exeption provides information on a database access error.
     */
    boolean deletById(Integer id) throws SQLException;

    /**
     * @param idFlm will find film .
     * @return boolean - result of method return.
     * true - if it goes well and
     * false - if removing was roll back.
     * @exeption provides information on a database access error.
     */
    boolean findById(Integer idFlm) throws SQLException;

    /**
     * @param film will make another film in mysql table.
     * @exeption provides information on a database access error.
     */
    boolean insert(Film film) throws SQLException;

    /**
     * @param idFlm will find film .
     * @return Film with given id.
     * @exeption provides information on a database access error.
     */
    Film selectById(int idFlm) throws SQLException;
}
