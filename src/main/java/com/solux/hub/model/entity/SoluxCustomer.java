package com.solux.hub.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "solux_customer")
public class SoluxCustomer {

    @Id
    @Column(name = "id")
    private int cust_id;

    @Column(name = "name")
    private String cust_name;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private double capacity;

    @OneToOne
    @JoinColumn(name = "hub_id", nullable = false)
    private SoluxHub soluxHub;

    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = false)
    private SoluxDealer soluxDealer;

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setSoluxHub(SoluxHub soluxHub) {
        this.soluxHub = soluxHub;
    }

    public void setSoluxDealer(SoluxDealer soluxDealer) {
        this.soluxDealer = soluxDealer;
    }

    public int getCust_id() {
        return cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public String getLocation() {
        return location;
    }

    public double getCapacity() {
        return capacity;
    }

    public SoluxHub getSoluxHub() {
        return soluxHub;
    }

    public SoluxDealer getSoluxDealer() {
        return soluxDealer;
    }
}
