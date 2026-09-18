package com.muhammettd.application.service;

import com.muhammettd.application.dto.CustomerCreateRequest;
import com.muhammettd.application.model.Customer;
import com.muhammettd.application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CustomerCreateRequest request) {
        Customer customer = new Customer(
                request.id(),
                request.firstName(),
                request.lastName(),
                request.phone(),
                request.email()
        );

        return customerRepository.save(customer);

    }

}
