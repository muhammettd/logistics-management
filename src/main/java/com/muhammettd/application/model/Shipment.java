package com.muhammettd.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "shipments")
public class Shipment {

    @Id
    private String id;

    private Customer sender;
    private Customer receiver;

    private Driver driver;

    private double pickupLatitude;
    private double pickupLongitude;

    private double dropoffLatitude;
    private double dropoffLongitude;

    private double totalAmount;

    private ShipmentStatus shipmentStatus;

    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;

    private double weightKg;

    public Shipment() {
    }

    public Shipment(Customer sender, Customer receiver, Driver driver,
                    double pickupLatitude, double pickupLongitude, double dropoffLatitude,
                    double dropoffLongitude, double totalAmount, ShipmentStatus shipmentStatus,
                    LocalDateTime createdAt, LocalDateTime deliveredAt, double weightKg) {
        this.sender = sender;
        this.receiver = receiver;
        this.driver = driver;
        this.pickupLatitude = pickupLatitude;
        this.pickupLongitude = pickupLongitude;
        this.dropoffLatitude = dropoffLatitude;
        this.dropoffLongitude = dropoffLongitude;
        this.totalAmount = totalAmount;
        this.shipmentStatus = shipmentStatus;
        this.createdAt = createdAt;
        this.deliveredAt = deliveredAt;
        this.weightKg = weightKg;
    }

    public String getId() {
        return id;
    }

    public Customer getSender() {
        return sender;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getPickupLatitude() {
        return pickupLatitude;
    }

    public double getPickupLongitude() {
        return pickupLongitude;
    }

    public double getDropoffLatitude() {
        return dropoffLatitude;
    }

    public double getDropoffLongitude() {
        return dropoffLongitude;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setPickupLatitude(double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public void setPickupLongitude(double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public void setDropoffLatitude(double dropoffLatitude) {
        this.dropoffLatitude = dropoffLatitude;
    }

    public void setDropoffLongitude(double dropoffLongitude) {
        this.dropoffLongitude = dropoffLongitude;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}
