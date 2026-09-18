package com.muhammettd.application.dto;

import com.muhammettd.application.model.Address;

public record AddressResponse(
        String id,
        String title,
        String fullAddress,
        String city,
        double latitude,
        double longitude
) {

    public static AddressResponse convert(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getTitle(),
                address.getFullAddress(),
                address.getCity(),
                address.getLatitude(),
                address.getLongitude()
        );
    }


}
