package com.muhammettd.application.dto;

import org.springframework.data.redis.connection.RedisGeoCommands;

public record GeoResultDto(
        String courierId,
        double distanceInKm
) {

    public static GeoResultDto convert(org.springframework.data.geo.GeoResult<RedisGeoCommands.GeoLocation<String>> result) {
        return new GeoResultDto(
                result.getContent().getName(), // Kurye ID
                result.getDistance().getValue() // Mesafe (KM)
        );
    }


}

