package com.solux.hub.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "solux_dealer")
public class SoluxDealer {

    @Id
    @Column(name = "dealer_id")
    private String dealerId;

    @Column(name = "name")
    private String dealerName;

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerId() {
        return dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }
}
