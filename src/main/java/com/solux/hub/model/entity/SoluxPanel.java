package com.solux.hub.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.type.TimestampType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "solux_panel")
public class SoluxPanel {

    @Id
    @Column(name = "panel_id")
    private String panel_id;

    @Column(name = "volt")
    private double volt;

    @Column(name = "curr")
    private double curr;

    @Column(name = "power")
    private double power;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hub_id", nullable = false)
    private SoluxHub soluxHub;

    public void setPanel_id(String panel_id) {
        this.panel_id = panel_id;
    }

    public void setVolt(double volt) {
        this.volt = volt;
    }

    public void setCurr(double curr) {
        this.curr = curr;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setTimestampType(Date date) {
        this.timestamp = date;
    }

    public void setSoluxHub(SoluxHub soluxHub) {
        this.soluxHub = soluxHub;
    }

    public String getPanel_id() {
        return panel_id;
    }

    public double getVolt() {
        return volt;
    }

    public double getCurr() {
        return curr;
    }

    public double getPower() {
        return power;
    }

    public Date getTimestampType() {
        return timestamp;
    }

    public SoluxHub getSoluxHub() {
        return soluxHub;
    }
}
