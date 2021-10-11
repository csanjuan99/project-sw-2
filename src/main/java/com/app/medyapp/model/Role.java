package com.app.medyapp.model;

import com.app.medyapp.enums.ERole;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "role", schema = "medyapp")
public class Role implements Serializable {

    private static final long serialVersionUID = -5112788970587137137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private int roleId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ERole roleName;

    public Role(@NotNull ERole roleName) {
        this.roleName = roleName;
    }

    public ERole getRoleName() {
        return roleName;
    }

    public void setRoleName(ERole roleName) {
        this.roleName = roleName;
    }
}
