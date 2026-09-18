package com.muhammettd.application.controller;

import com.muhammettd.application.dto.AddressCreateRequest;
import com.muhammettd.application.dto.AddressResponse;
import com.muhammettd.application.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressCreateRequest request) {
        return ResponseEntity.ok(addressService.createAddress(request));
    }

    // Bir müşterinin kayıtlı tüm adreslerini listeleme
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AddressResponse>> getAddressesByCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(addressService.getAddressesByCustomerId(customerId));
    }

}
