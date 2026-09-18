package com.muhammettd.application.controller;

import com.muhammettd.application.dto.ShipmentCreateRequest;
import com.muhammettd.application.dto.ShipmentResponse;
import com.muhammettd.application.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;


    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(@RequestBody ShipmentCreateRequest request) {
        return ResponseEntity.ok(shipmentService.createAndDispatch(request));
    }

    


}

