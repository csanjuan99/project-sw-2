package com.app.medyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "beneficiary", schema = "medyapp")

public class Beneficiary implements Serializable {
    private static final long serialVersionUID = 2973913326210723006L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beneficiary_id")
    private int beneficiaryId;

    @Column(name = "beneficiary_dni")
    private int beneficiaryDni;

    @Column(name = "beneficiary_name", nullable = false)
    private String beneficiaryName;

    @Column(name = "beneficiary_lastname", nullable = false)
    private String beneficiaryLastname;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    // Constructor sin ID
    public Beneficiary(int beneficiaryDni, String beneficiaryName, String beneficiaryLastname, User user) {
        this.beneficiaryDni = beneficiaryDni;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryLastname = beneficiaryLastname;
        this.user = user;
    }

    public Beneficiary(int beneficiaryDni, String beneficiaryName, String beneficiaryLastname) {
        this.beneficiaryDni = beneficiaryDni;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryLastname = beneficiaryLastname;
    }
}
