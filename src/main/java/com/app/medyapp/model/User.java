package com.app.medyapp.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "user", schema = "medyapp")
public class User implements Serializable {

    private static final long serialVersionUID = -1177541923493686973L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotNull
    @Column(name = "user_email", unique = true)
    private String userEmail;

    @NotNull
    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_dni", nullable = false)
    private int userDni;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_lastname", nullable = false)
    private String userLastname;

    @JoinTable(
            name = "user_service",
            joinColumns = @JoinColumn(name = "fk_user", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_service", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Service> service;

    @NotNull
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "subscription_id", referencedColumnName = "subscription_id")
    private Subscription subscription;

    public User(@NotNull String userEmail,
                @NotNull String userPassword,
                @NotNull String userName,
                @NotNull String userLastname,
                @NotNull int userDni) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userLastname = userLastname;
        this.userDni = userDni;
    }

    public void addService(Service service) {
        if (this.service == null) {
            this.service = new ArrayList<>();
        }
        this.service.add(service);
    }

}
