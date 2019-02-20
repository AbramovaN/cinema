package com.abramova.cinema.entities;

import java.io.Serializable;

public class User implements Serializable {

    private int idUser;
    private int idUserType;
    private String name;
    private String surname;
    private int operatorCode;
    private int phoneNumber;
    private String email;
    private String password;

    public User() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(int operatorCode) {
        this.operatorCode = operatorCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        User obj = (User) o;
        return idUserType == obj.idUserType &&
                idUser == obj.idUser &&
                obj.name != null && obj.name.equals(name) &&
                obj.surname != null && obj.surname.equals(surname) &&
                operatorCode == obj.operatorCode &&
                phoneNumber == obj.phoneNumber &&
                obj.password != null && obj.password.equals(password) &&
                obj.email != null && obj.email.equals(email);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + idUser;
        result = 31 * result + idUserType;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (surname == null ? 0 : surname.hashCode());
        result = 31 * result + operatorCode;
        result = 31 * result + phoneNumber;
        result = 31 * result + (email != null ? 0 : email.hashCode());
        result = 31 * result + (password != null ? 0 : password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", idUserType=" + idUserType +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", operatorCode=" + operatorCode +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", password=" + password + '}';
    }
}
