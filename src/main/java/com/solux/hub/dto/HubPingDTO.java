package com.solux.hub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HubPingDTO {

    private String id;
    private Data data;

    public void setId(String id) {
        this.id = id;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public Data getData() {
        return data;
    }

    public static class Data {

        @JsonProperty("panels")
        private List<Panel> panelList;

        private float temp;

        public void setPanelList(List<Panel> panelList) {
            this.panelList = panelList;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public List<Panel> getPanelList() {
            return panelList;
        }

        public float getTemp() {
            return temp;
        }
    }

    public static class Panel {

        private String panel_id;
        private float volt;
        private float curr;

        public void setPanel_id(String panel_id) {
            this.panel_id = panel_id;
        }

        public void setVolt(float volt) {
            this.volt = volt;
        }

        public void setCurr(float curr) {
            this.curr = curr;
        }

        public String getPanel_id() {
            return panel_id;
        }

        public float getVolt() {
            return volt;
        }

        public float getCurr() {
            return curr;
        }
    }
}
