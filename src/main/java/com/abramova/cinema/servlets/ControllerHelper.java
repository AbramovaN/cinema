package com.abramova.cinema.servlets;

import com.abramova.cinema.commands.*;


import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class ControllerHelper {
    private static ControllerHelper instance = null;
    HashMap<String, Command> commands = new HashMap<String, Command>();

    private ControllerHelper() {
        commands.put("login", new LoginUserCommand());
        commands.put("add", new AddUserCommand());
        commands.put("films", new ListOfFilmsCommand());
        commands.put("hallLayout", new HallLayoutCommand());
        commands.put("deleteFilm", new DeletFilmCommand());
        commands.put("createFilm", new CreateFilmCommand());
        commands.put("changeLocale", new ChangeLocalCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("listOfOrders", new BookTicketCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        return commands.get(request.getParameter("command"));
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}
