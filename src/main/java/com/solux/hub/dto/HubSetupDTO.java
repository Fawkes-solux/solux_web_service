package com.solux.hub.dto;

import java.util.List;

public class HubSetupDTO {

    private String id;
    private float lat;
    private float lng;
    private List<String> panelId;

    public HubSetupDTO(String id, float lat, float lng, List<String> panelId) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.panelId = panelId;
    }

    public HubSetupDTO() {

    }

    public String getId() {
        return id;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public List<String> getPanelId() {
        return panelId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setPanelId(List<String> panelId) {
        this.panelId = panelId;
    }
}
