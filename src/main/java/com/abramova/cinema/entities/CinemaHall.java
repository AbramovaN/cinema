package com.abramova.cinema.entities;

import java.io.Serializable;

public class CinemaHall implements Serializable {
    private int idCinemaHall;
    private int row;
    private int place;


    public CinemaHall() {
    }

    public CinemaHall(int idCinemaHall, int row, int place) {
        this.idCinemaHall = idCinemaHall;
        this.row = row;
        this.place = place;
    }

    public CinemaHall(int idCinemaHall) {
        this.idCinemaHall = idCinemaHall;
    }

    public int getIdCinemaHall() {
        return idCinemaHall;
    }

    public void setIdCinemaHall(int idCinemaHall) {
        this.idCinemaHall = idCinemaHall;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CinemaHall)) return false;
        CinemaHall cinemaHall = (CinemaHall) o;
        return getIdCinemaHall() == cinemaHall.getIdCinemaHall() &&
                getRow() == cinemaHall.getRow() &&
                getPlace() == cinemaHall.getPlace();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + idCinemaHall;
        result = 31 * result + row;
        result = 31 * result + place;
        return result;
    }

    @Override
    public String toString() {
        return "Cinema hall{" +
                "idCinemaHall=" + idCinemaHall +
                ", row=" + row +
                ", place=" + place +
                '}';
    }
}
