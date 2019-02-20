package com.abramova.cinema.commands;

import com.abramova.cinema.manager.Config;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    Logger logger = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(1);
        logger.info(request.getSession().getAttribute("user") + " sing out");
        request.getSession().setAttribute("user", null);
        return Config.getInstance().getProperty("HOME");
    }
}
