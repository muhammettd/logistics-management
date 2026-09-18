package com.muhammettd.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressCreateRequest(
        @NotBlank(message = "Müşteri ID zorunludur")
        String customerId,

        @NotBlank(message = "Adres başlığı (Örn: Ev) boş olamaz")
        String title,

        @NotBlank(message = "Şehir boş olamaz")
        String city,

        @NotBlank(message = "Açık adres detayları boş olamaz")
        String fullAddress,

        @NotNull(message = "Enlem (Latitude) zorunludur")
        Double latitude,

        @NotNull(message = "Boylam (Longitude) zorunludur")
        Double longitude
) {
}
