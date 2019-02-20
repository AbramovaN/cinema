package com.abramova.cinema.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Film implements Serializable {

    private int idFilm;
    private String filmName;
    private String genre;
    private String description;
    private float price;
    private Date date;
    private Time time;

    public Film() {
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Film obj = (Film) o;
        return idFilm == obj.idFilm &&
                filmName != null && filmName.equals(obj.filmName) &&
                genre != null && genre.equals(obj.genre) &&
                description != null && description.equals(obj.description) &&
                date != null && date.equals(obj.date) &&
                time != null && time.equals(obj.time) &&
                price == obj.price;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + idFilm;
        result = 31 * result + (filmName == null ? 0 : filmName.hashCode());
        result = 31 * result + (genre == null ? 0 : genre.hashCode());
        result = 31 * result + (description == null ? 0 : description.hashCode());
        result = 31 * result + (date == null ? 0 : date.hashCode());
        result = 31 * result + (time == null ? 0 : time.hashCode());
        result = 31 * result + Float.floatToIntBits(price);
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "idFilm=" + idFilm +
                ", filmName='" + filmName + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", price=" + price +
                '}';
    }
}
