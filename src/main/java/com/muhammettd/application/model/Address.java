package com.muhammettd.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
public class Address {

    @Id
    private String id;

    // Hangi müşteriye ait olduğunu tutuyoruz (Foreign Key mantığı)
    private String customerId;

    private String title; // Örn: "Ev", "İş", "Antalya Yazlık"
    private String fullAddress; // Açık adres metni

    private String city;

    // Sipariş modülüne bu koordinatları göndereceğiz
    private double latitude;
    private double longitude;

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTitle() {
        return title;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getCity() {
        return city;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
