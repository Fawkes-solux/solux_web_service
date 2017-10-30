package com.solux.hub.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "solux_hub")
public class SoluxHub implements Serializable{

    @Id
    @Column(name = "hub_id")
    private String hub_id;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

    public String getHub_id() {
        return hub_id;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setHub_id(String hub_id) {
        this.hub_id = hub_id;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
