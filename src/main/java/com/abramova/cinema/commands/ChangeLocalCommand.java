package com.abramova.cinema.commands;

import com.abramova.cinema.manager.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocalCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("locale", new java.util.Locale(request.getParameter("local")));
        return Config.getInstance().getProperty(Config.HOME);
    }
}
