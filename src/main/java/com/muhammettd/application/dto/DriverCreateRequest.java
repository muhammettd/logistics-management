package com.muhammettd.application.dto;

import jakarta.validation.constraints.NotBlank;

public record DriverCreateRequest(
        @NotBlank(message = "İd boş olamaz")
        String id,

        @NotBlank(message = "Kurye adı boş olamaz")
        String name,

        @NotBlank(message = "Kurye soyadı boş olamaz")
        String surname,

        @NotBlank(message = "Telefon numarası zorunludur")
        String phoneNumber

) {
}
