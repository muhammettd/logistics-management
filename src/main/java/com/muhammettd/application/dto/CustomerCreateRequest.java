package com.muhammettd.application.dto;

public record CustomerCreateRequest(
        String id,
        String firstName,
        String lastName,
        String phone,
        String email
) {
}
