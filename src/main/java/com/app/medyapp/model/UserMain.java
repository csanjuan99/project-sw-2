package com.app.medyapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserMain implements UserDetails, Serializable {

    private static final long serialVersionUID = -1197561919765559803L;

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserMain(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    //Metodo que asigna los privilegios (autorización)
    public static UserMain build(User user) {
        //Convertimos la clase Rol a la clase GrantedAuthority
        List<GrantedAuthority> authorities =
                user.getRole()
                        .stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getRoleName().name()))
                        .collect(Collectors.toList());
        return new UserMain(user.getUserEmail(),
                user.getUserPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
