package com.muhammettd.application.service;

import com.muhammettd.application.dto.DriverCreateRequest;
import com.muhammettd.application.dto.DriverResponse;
import com.muhammettd.application.model.Driver;
import com.muhammettd.application.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    Logger log = LoggerFactory.getLogger(DriverService.class);

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    public DriverResponse createDriver(DriverCreateRequest request) {
        Driver courier = new Driver(
          request.id(), request.name(), request.surname(), request.phoneNumber());

        driverRepository.save(courier);

        return DriverResponse.convert(courier);

    }

    public DriverResponse getDriverById(String id) {
        Driver courier = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kurye bulunamadı: " + id));
        return DriverResponse.convert(courier);
    }

    public List<DriverResponse> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(DriverResponse::convert)
                .collect(Collectors.toList());
    }

    public DriverResponse updateStatus(String id) {
        Driver courier = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kurye bulunamadı: " + id));

        Driver updatedCourier = driverRepository.save(courier);

        return DriverResponse.convert(courier);
    }


}
