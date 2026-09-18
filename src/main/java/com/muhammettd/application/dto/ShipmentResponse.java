package com.muhammettd.application.dto;

import com.muhammettd.application.model.Shipment;
import com.muhammettd.application.model.ShipmentStatus;

public class ShipmentResponse {

    String shipmentId;
    ShipmentStatus status;
    String assignedDriverId; // Atanan şoför varsa dönecek
    String message;

    public ShipmentResponse() {
    }

    public ShipmentResponse(String shipmentId, ShipmentStatus status, String assignedDriverId, String message) {
        this.shipmentId = shipmentId;
        this.status = status;
        this.assignedDriverId = assignedDriverId;
        this.message = message;
    }

    public static ShipmentResponse convert(Shipment shipment) {
        return new ShipmentResponse(
                shipment.getId(),
                shipment.getShipmentStatus(),
                shipment.getDriver().getId(),
                ""
        );
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public String getAssignedDriverId() {
        return assignedDriverId;
    }

    public String getMessage() {
        return message;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public void setAssignedDriverId(String assignedDriverId) {
        this.assignedDriverId = assignedDriverId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}