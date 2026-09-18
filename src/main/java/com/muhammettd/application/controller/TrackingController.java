package com.muhammettd.application.controller;

import com.muhammettd.application.dto.LocationEvent;
import com.muhammettd.application.messaging.LocationProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tracking")
public class TrackingController {

    private final LocationProducer locationProducer;

    public TrackingController(LocationProducer locationProducer) {
        this.locationProducer = locationProducer;
    }

    /**
     * Test amaçlı: Postman üzerinden kurye konumunu simüle eder
     */
    @PostMapping("/simulate")
    public ResponseEntity<String> simulateLocationUpdate(@RequestBody LocationEvent event) {
        // HTTP isteği anında sonlanır, işi arka planda Kafka çözer!
        locationProducer.publishLocation(event);
        return ResponseEntity.ok("Konum Kafka kuyruğuna bırakıldı.");
    }

}
