package com.app.medyapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "invoice", schema = "medyapp")

public class Invoice implements Serializable {
    private static final long serialVersionUID = 8770144604580840973L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private int invoiceId;

    @Column(name = "invoice_value", nullable = false)
    private double invoiceValue;

    @Temporal(TemporalType.DATE)
    @Column(name = "invoice_date", nullable = false)
    private Date invoiceDate;

}
