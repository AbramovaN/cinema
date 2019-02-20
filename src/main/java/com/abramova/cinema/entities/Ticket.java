package com.abramova.cinema.entities;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int idTicket;
    private int idUser;
    private int idFilm;
    private CinemaHall cinemaHall;
    private boolean booked;

    public Ticket() {
    }

    public Ticket(int idTicket, int idUser, int idFilm, CinemaHall cinemaHall, boolean booked) {
        this.idTicket = idTicket;
        this.idUser = idUser;
        this.idFilm = idFilm;
        this.cinemaHall = cinemaHall;
        this.booked = booked;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idOrder) {
        this.idUser = idOrder;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != getClass()) return false;
        Ticket obj = (Ticket) o;
        return idUser == obj.idUser &&
                cinemaHall == obj.cinemaHall &&
                idTicket == obj.idTicket &&
                booked == obj.booked &&
                idFilm == obj.idFilm;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + idFilm;
        result = 31 * result + idUser;
        result = 31 * result + idTicket;
        result = 31 * result + cinemaHall.hashCode();
        result = 31 * result + (booked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", cinemaHall=" + cinemaHall +
                ", idUser=" + idUser +
                ", idFilm=" + idFilm +
                ", booked=" + booked +
                '}';
    }
}
