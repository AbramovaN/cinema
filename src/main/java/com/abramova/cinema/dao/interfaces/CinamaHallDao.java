package com.abramova.cinema.dao.interfaces;


import com.abramova.cinema.entities.CinemaHall;

import java.sql.SQLException;
import java.util.List;

public interface CinamaHallDao {
    /**
     * @param idCinemaHall will remove cinema hall with this id .
     * @return boolean - result of method return.
     * true - if it goes well and
     * false - if removing was roll back.
     * @exeption provides information on a database access error.
     */
    boolean deletById(Integer idCinemaHall) throws SQLException;

    /**
     * @return List<CinemaHall> - result of method return List of all seats that  mysql table keep
     * @exeption provides information on a database access error
     */
    List<CinemaHall> selectAll() throws SQLException;

    /**
     * @param cinemaHall will make another seat in CinemaHall.
     * @return boolean - result of method return
     * @exeption provides information on a database access error
     */
    boolean insert(CinemaHall cinemaHall) throws SQLException;

    /**
     * @param cinemaHallId id of cinema hall that we need
     * @return Cinema Hall
     */
    CinemaHall selectSeatByCinemaHallId(int cinemaHallId) throws SQLException;
}
