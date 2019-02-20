
import com.abramova.cinema.entities.Film;
import com.abramova.cinema.service.FilmService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.List;


public class TestFilmService {

    private FilmService filmService;
    private Film film;

    String getDate = "2019-02-18";
    GregorianCalendar gregorianCalendar = new GregorianCalendar(Integer.valueOf(getDate.substring(0, 4)),
            Integer.valueOf(getDate.substring(5, 7)) - 1, Integer.valueOf(getDate.substring(8, 10)));
    Date date = new java.sql.Date(gregorianCalendar.getTimeInMillis());

    @Before
    public void setUp() {
        filmService = new FilmService();
        film = new Film();
        film.setIdFilm(5);
        film.setFilmName("How It Ends");
        film.setDescription("A desperate father tries to return home to his pregnant wife after a mysterious apocalyptic event turns everything to chaos.");
        film.setGenre("drame,detective");
        film.setPrice(50);
        film.setTime(Time.valueOf("14:20:00"));
        film.setDate(date);


    }

    @Test
    public void testFindUserByEmail() throws SQLException {
        List<Film> filmList = filmService.selectFilmsByDate(date);
        Assert.assertEquals(6, filmList.size());
        return;
    }

    @Test
    public void selectById() throws SQLException {
        Film film1 = filmService.selectFilmByID(5);
        Assert.assertEquals(film1, film);
        return;
    }

    @After
    public void tearDown() {
        filmService = null;
        film = null;
    }
}


