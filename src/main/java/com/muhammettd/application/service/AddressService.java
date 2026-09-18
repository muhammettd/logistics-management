package com.muhammettd.application.service;

import com.muhammettd.application.dto.AddressCreateRequest;
import com.muhammettd.application.dto.AddressResponse;
import com.muhammettd.application.model.Address;
import com.muhammettd.application.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private static final Logger log = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressResponse createAddress(AddressCreateRequest request) {
        log.info("Müşteri {} için yeni adres ekleniyor: {}", request.customerId(), request.title());

        Address address = new Address();
        address.setCustomerId(request.customerId());
        address.setTitle(request.title());
        address.setFullAddress(request.fullAddress());
        address.setCity(request.city());
        address.setLatitude(request.latitude());
        address.setLongitude(request.longitude());

        Address savedAddress = addressRepository.save(address);
        return AddressResponse.convert(savedAddress);
    }
    
    public List<AddressResponse> getAddressesByCustomerId(String customerId) {
        return addressRepository.findByCustomerId(customerId)
                .stream()
                .map(AddressResponse::convert)
                .toList();
    }

    public void deleteAddress(String id) {
        log.info("Adres siliniyor: {}", id);
        addressRepository.deleteById(id);
    }

}
