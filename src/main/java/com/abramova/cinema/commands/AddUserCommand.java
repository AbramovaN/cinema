package com.abramova.cinema.commands;

import com.abramova.cinema.entities.User;
import com.abramova.cinema.manager.Config;
import com.abramova.cinema.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AddUserCommand implements Command {
    private static Logger logger = Logger.getLogger(AddUserCommand.class);
    private String fullPhoneNumber;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserService userService = new UserService();
        User user = new User();
        user.setIdUserType(2);
        user.setPassword(request.getParameter("password"));
        if (userService.findUserByEmail(request.getParameter("email"))) {
            request.setAttribute("error", "User with this email already exists");
            logger.warn("User with this email already exists");
            return Config.getInstance().getProperty(Config.LOGIN);
        }
        user.setEmail(request.getParameter("email"));
        fullPhoneNumber = request.getParameter("phone_number");
        if (fullPhoneNumber != null) {
            user.setOperatorCode(Integer.valueOf(fullPhoneNumber.substring(0, 4)));
            user.setPhoneNumber(Integer.valueOf(fullPhoneNumber.substring(4)));
        }
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        if (userService.registerUser(user)) {
            logger.info("New user inserted successfully");
            return Config.getInstance().getProperty(Config.LOGIN);
        }
        return Config.getInstance().getProperty(Config.REGISTRATION);
    }
}
