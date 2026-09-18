package com.muhammettd.application.dto;

import com.muhammettd.application.model.Driver;

public record DriverResponse(
        String id,
        String name,
        String surname,
        String phoneNumber
) {

    public static DriverResponse convert(Driver courier) {
        return new DriverResponse(
                courier.getId(),
                courier.getName(),
                courier.getSurname(),
                courier.getPhoneNumber()
        );
    }

}
