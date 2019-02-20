package com.abramova.cinema.commands;

import com.abramova.cinema.manager.Config;
import com.abramova.cinema.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginUserCommand implements Command {
    Logger logger = Logger.getLogger(LoginUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        if (request.getParameter("login") != null && request.getParameter("password") != null) {
            if (!userService.findUserByEmailAndPassword(request.getParameter("login"), request.getParameter("password"))) {
                request.setAttribute("error", "Wrong email address or password");
                logger.info("Wrong email address or password");
                return Config.getInstance().getProperty(Config.LOGIN);
            }
            session.setAttribute("user", request.getParameter("login"));
            logger.info(request.getParameter("login") + " sing in");
            return userService.findUserTypeIdByEmail(request.getParameter("login")) == 1 ?
                    Config.getInstance().getProperty(Config.ADMIN) :
                    Config.getInstance().getProperty(Config.LIST_OF_FILMS);
        }
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}


