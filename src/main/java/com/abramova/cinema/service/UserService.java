package com.abramova.cinema.service;

import com.abramova.cinema.dao.DAOFactory;
import com.abramova.cinema.dao.interfaces.UserDao;
import com.abramova.cinema.entities.User;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = DAOFactory.getUserDAO();
    }

    /**
     * Method that check all emails from database and return true if find match with given.
     *
     * @param email is email that you want to check for a match.
     * @return true if such email in database exist otherwise false.
     */
    public boolean findUserByEmail(String email) throws SQLException {
        return userDao.findByEmail(email);
    }

    /**
     * Method that add new user to database.
     *
     * @param user is user that you want to add.
     * @return true if it goes well otherwise false.
     */
    public boolean registerUser(User user) throws SQLException {
        return userDao.insert(user);
    }

    /**
     * Method that check all emails and passwords from database and return true if find match with given.
     *
     * @param email    is email that you want to check for a match.
     * @param password is password that you want to check for a match.
     * @return true if such email and password in database exist otherwise false.
     */
    public boolean findUserByEmailAndPassword(String email, String password) throws SQLException {
        return userDao.findByEmailAndPassword(email, password);
    }

    /**
     * Method that get type of user by given email.
     *
     * @param email is email of user .
     * @return user type id.
     */
    public int findUserTypeIdByEmail(String email) throws SQLException {
        return userDao.findIdTypeByEmail(email);
    }

    /**
     * Method that check all emails from database and return user  with such email.
     *
     * @param email is email that you want to find user by.
     * @return user with given email.
     */
    public User findByEmailUser(String email) throws SQLException {
        return userDao.findUserByEmail(email);
    }

}
