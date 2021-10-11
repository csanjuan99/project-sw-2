package com.app.medyapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class NewUserDTO {

    @NotNull
    private int userId;
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;
    @NotNull
    private int userDni;
    @NotBlank
    private String userName;
    @NotBlank
    private String userLastname;

    //Por defecto crea un usuario normal
    //Si quiero un usuario Admin debo pasar este campo roles
    private Set<String> role = new HashSet<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserDni() {
        return userDni;
    }

    public void setUserDni(int userDni) {
        this.userDni = userDni;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

}
