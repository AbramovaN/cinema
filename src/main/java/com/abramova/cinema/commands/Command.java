package com.abramova.cinema.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    /**
     * Method that handles HttpServletRequest and HttpServletResponse based on the parameters
     *
     * @param request  HttpServletRequest.
     * @param response HttpServletResponse.
     * @return String that is URL
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
}