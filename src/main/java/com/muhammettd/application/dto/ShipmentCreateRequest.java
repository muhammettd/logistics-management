package com.muhammettd.application.dto;

public record ShipmentCreateRequest(
        String senderId,
        String receiverId,

        // Kargonun Alınacağı (Gönderici) Adres/Koordinat
        double pickupLatitude,
        double pickupLongitude,

        double totalAmount,

        // Kargonun Gideceği (Alıcı) Adres/Koordinat
        double deliveryLatitude,
        double deliveryLongitude,
        double weightKg
) {




}
