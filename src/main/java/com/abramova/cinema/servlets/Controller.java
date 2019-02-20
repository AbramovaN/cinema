package com.abramova.cinema.servlets;

import com.abramova.cinema.commands.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Controller extends HttpServlet {
    Logger logger = Logger.getLogger(Controller.class);
    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            logger.warn(e);
        }
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            logger.warn(e);
        }
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String page = null;
        try {
            Command command = controllerHelper.getCommand(request);
            page = command.execute(request, response);
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException | NullPointerException e) {
            logger.warn(e);
        }
    }

}