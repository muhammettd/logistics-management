package com.muhammettd.application.controller;

import com.muhammettd.application.service.DispatchService;
import com.muhammettd.application.service.RedisLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dispatch")
public class DispatchController {

    private final RedisLocationService redisLocationService;
    private final DispatchService dispatchService;


    public DispatchController(RedisLocationService redisLocationService, DispatchService dispatchService) {
        this.redisLocationService = redisLocationService;
        this.dispatchService = dispatchService;
    }

    @GetMapping("/nearest-driver")
    public ResponseEntity<String> findNearestDriver(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "5.0") double radiusKm) {

        String nearestDriverId = redisLocationService.findNearbyDrivers(latitude, longitude, radiusKm);

        if (nearestDriverId == null) {
            return ResponseEntity.ok(radiusKm + " km yarıçapında uygun/aktif bir şoför bulunamadı.");
        }

        return ResponseEntity.ok("En Yakın Şoför ID: " + nearestDriverId);
    }

}
