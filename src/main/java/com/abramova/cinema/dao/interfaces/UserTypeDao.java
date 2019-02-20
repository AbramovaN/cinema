package com.abramova.cinema.dao.interfaces;

import com.abramova.cinema.entities.UserType;

import java.sql.SQLException;
import java.util.List;

public interface UserTypeDao {
    /**
     * @return List<UserType> - result of method return
     * List of all user types that  mysql table keep
     * @exeption provides information on a database access error
     */
    public List<UserType> selectAll() throws SQLException;

    /**
     * @param id will remove user type .
     * @return boolean - result of method return
     * true - if it goes well and
     * false - if removing was roll back
     * @exeption provides information on a database access error
     */
    public boolean deletById(Integer id) throws SQLException;

    /**
     * @param userType will make another userType in mysql table.
     * @exeption provides information on a database access error
     */
    public void insert(UserType userType) throws SQLException;
}
