package com.app.medyapp.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDTO {

    private String token;
    private String bearer = "Bearer";
    private String userEmail;
    private String userName;
    private String userLastname;
    private int userDni;
    private int userId;
    private String userPassword;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDTO(String token, String userEmail, String userName, String userLastname, int userDni, int userId, String userPassword, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.userId = userId;
        this.userDni = userDni;
        this.userName = userName;
        this.userLastname = userLastname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
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

    public int getUserDni() {
        return userDni;
    }

    public void setUserDni(int userDni) {
        this.userDni = userDni;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
