package com.abramova.cinema.entities;

import java.io.Serializable;

public class UserType implements Serializable {

    private int idUserType;
    private String userType;

    public UserType() {
    }

    public int getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String usertype) {
        this.userType = usertype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != getClass()) return false;
        UserType obj = (UserType) o;
        return idUserType == obj.idUserType &&
                userType != null && userType.equals(obj.userType);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + idUserType;
        return 31 * result + (userType == null ? 0 : userType.hashCode());
    }

    @Override
    public String toString() {
        return "UserType{" +
                "idUserType=" + idUserType +
                ", userType='" + userType + '\'' +
                '}';
    }
}
