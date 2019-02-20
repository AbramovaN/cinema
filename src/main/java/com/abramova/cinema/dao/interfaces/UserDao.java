package com.abramova.cinema.dao.interfaces;

import com.abramova.cinema.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /**
     * @param id will remove user .
     * @return boolean - result of method return
     * true - if it goes well and
     * false - if removing was roll back
     * @exeption provides information on a database access error
     */
    boolean deletById(Integer id) throws SQLException;

    /**
     * @return List<User> - result of method return
     * List of all users that  mysql table keep
     * @exeption provides information on a database access error
     */
    List<User> selectAll() throws SQLException;

    /**
     * @param user will make another user in mysql table.
     * @exeption provides information on a database access error
     */
    boolean insert(User user) throws SQLException;

    /**
     * @param email and password will find  user .
     * @return User
     * @exeption provides information on a database access error
     */
    boolean findByEmailAndPassword(String email, String password) throws SQLException;

    /**
     * @param email will find  user .
     * @return User
     * @exeption provides information on a database access error
     */
    boolean findByEmail(String email) throws SQLException;

    /**
     * @param email will find  user id
     * @return IdUserType
     * @exeption provides information on a database access error
     */
    int findIdTypeByEmail(String email) throws SQLException;

    /**
     * @param email will find  user id
     * @return IdUser
     * @exeption provides information on a database access error
     */
    User findUserByEmail(String email) throws SQLException;
}
