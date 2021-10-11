package com.app.medyapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "service", schema = "medyapp")

public class Service implements Serializable {
    private static final long serialVersionUID = -2159751320014045861L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int service_id;

    @Column(name = "service_name")
    private String service_name;

    @Column(name = "service_value", nullable = false)
    private double service_value;

    @Temporal(TemporalType.DATE)
    @Column(name = "service_date", nullable = false)
    private Date service_date;

    @ManyToMany(mappedBy = "service")
    private List<User> user;

    public Service(int service_id, String service_name, double service_value, Date service_date, List<User> user) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.service_value = service_value;
        this.service_date = service_date;
        this.user = user;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public double getService_value() {
        return service_value;
    }

    public void setService_value(double service_value) {
        this.service_value = service_value;
    }

    public Date getService_date() {
        return service_date;
    }

    public void setService_date(Date service_date) {
        this.service_date = service_date;
    }

    public List<User> getAffiliate() {
        return user;
    }

    public void setAffiliate(List<User> affiliate) {
        this.user = user;
    }
}
