package com.solux.hub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class DealerBoardDTO {

    @JsonProperty("errorFlag")
    private boolean errorFlag;

    @JsonProperty("errorMsg")
    private String erroMsg;

    @JsonProperty("data")
    private Data data;

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    public void setErroMsg(String erroMsg) {
        this.erroMsg = erroMsg;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public String getErroMsg() {
        return erroMsg;
    }

    public Data getData() {
        return data;
    }

    public static class Data {

        @JsonProperty("id")
        private String dealerId;

        @JsonProperty("name")
        private String dealerName;

        @JsonProperty("total_capacity")
        private double totalCapacity;

        @JsonProperty("total_effeciency")
        private double totalEffeciency;

        @JsonProperty("total_consumption")
        private Metrics metrics;

        private Customer customer;

        public void setDealerId(String dealerId) {
            this.dealerId = dealerId;
        }

        public void setDealerName(String dealerName) {
            this.dealerName = dealerName;
        }

        public void setTotalCapacity(double totalCapacity) {
            this.totalCapacity = totalCapacity;
        }

        public void setTotalEffeciency(double totalEffeciency) {
            this.totalEffeciency = totalEffeciency;
        }

        public void setMetrics(Metrics metrics) {
            this.metrics = metrics;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public String getDealerId() {
            return dealerId;
        }

        public String getDealerName() {
            return dealerName;
        }

        public double getTotalCapacity() {
            return totalCapacity;
        }

        public double getTotalEffeciency() {
            return totalEffeciency;
        }

        public Metrics getMetrics() {
            return metrics;
        }

        public Customer getCustomer() {
            return customer;
        }
    }

    public static class Customer {

        @JsonProperty("id")
        private int customerId;

        @JsonProperty("name")
        private String customerName;

        @JsonProperty("location")
        private String location;

        @JsonProperty("lat")
        private double lat;

        @JsonProperty("lng")
        private double lng;

        @JsonProperty("capacity")
        private double capacity;

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public void setCapacity(double capacity) {
            this.capacity = capacity;
        }

        public int getCustomerId() {
            return customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getLocation() {
            return location;
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }

        public double getCapacity() {
            return capacity;
        }
    }

    public static class Metrics {

        @JsonProperty("data")
        private List<MetricsData>  dataItems;

        public void setDataItems(List<MetricsData> dataItems) {
            this.dataItems = dataItems;
        }

        public List<MetricsData> getDataItems() {
            return dataItems;
        }
    }

    public static class MetricsData {

        @JsonProperty("type")
        private String type;

        @JsonProperty("value")
        private double value;

        @JsonProperty("timestamp")
        private Date timestamp;

        public void setType(String type) {
            this.type = type;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public String getType() {
            return type;
        }

        public double getValue() {
            return value;
        }

        public Date getTimestamp() {
            return timestamp;
        }
    }
}
