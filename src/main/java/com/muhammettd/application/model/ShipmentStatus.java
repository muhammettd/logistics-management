package com.muhammettd.application.model;

public enum ShipmentStatus {
    CREATED,         // Restoran siparişi hazırlıyor
    AT_BRANCH, // Hazır, en yakın kurye aranıyor
    IN_TRANSIT,        // Kurye teslim aldı, yolda
    OUT_FOR_DELIVERY,         // Teslim edildi
    DELIVERED
}
