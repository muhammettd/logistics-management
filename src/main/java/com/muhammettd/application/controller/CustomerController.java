package com.muhammettd.application.controller;

import com.muhammettd.application.dto.CustomerCreateRequest;
import com.muhammettd.application.model.Customer;
import com.muhammettd.application.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.PrimitiveIterator;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerCreateRequest request) {
        // Yeni bir müşteri oluşturur ve kaydeder
        Customer savedCustomer = customerService.createCustomer(request);
        return ResponseEntity.ok(savedCustomer);
    }


}
