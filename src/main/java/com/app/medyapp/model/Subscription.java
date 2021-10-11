package com.app.medyapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "subscription", schema = "medyapp")

public class Subscription implements Serializable {
    private static final long serialVersionUID = -7193506203263344320L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private long subscriptionId;

    @Column(name = "subscription_type", nullable = false)
    private long subscriptionType;

    @Column(name = "subscription_expiration", nullable = false)
    private Date subscriptionExpiration;

    @OneToOne()
    @JoinColumn(name = "invoice_id", insertable = false, updatable = false)
    private Invoice invoice;

}
