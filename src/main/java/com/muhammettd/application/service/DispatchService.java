package com.muhammettd.application.service;

import com.muhammettd.application.model.Driver;
import com.muhammettd.application.model.Shipment;
import com.muhammettd.application.model.ShipmentStatus;
import com.muhammettd.application.repository.DriverRepository;
import com.muhammettd.application.repository.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DispatchService {

    private final RedisLocationService redisLocationService;
    private final DriverRepository driverRepository;
    private final ShipmentRepository shipmentRepository;

    Logger log = LoggerFactory.getLogger(DispatchService.class);

    private static final double SEARCH_RADIUS_KM = 5.0;

    public DispatchService(RedisLocationService redisLocationService, DriverRepository driverRepository,
                           ShipmentRepository shipmentRepository) {
        this.redisLocationService = redisLocationService;
        this.driverRepository = driverRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public Shipment dispatchShipmentToNearestDriver(Shipment shipment) {
        log.info("Görevlendirme başlatıldı. Shipment ID: {}", shipment.getId());

        String nearestDriverId = redisLocationService.findNearbyDrivers(
                shipment.getPickupLongitude(),
                shipment.getPickupLatitude(),
                SEARCH_RADIUS_KM
        );

        if (nearestDriverId == null) {
            log.warn("Yakında uygun şoför bulunamadı! Kargo beklemeye alındı");
            shipment.setShipmentStatus(ShipmentStatus.CREATED);
            return shipmentRepository.save(shipment);
        }

        Optional<Driver> driverOpt = driverRepository.findById(nearestDriverId);

        if (driverOpt.isPresent()) {
            Driver driver = driverOpt.get();

            shipment.setDriver(driver);
            shipment.setShipmentStatus(ShipmentStatus.AT_BRANCH);

            log.info("Kargo başarıyla şoföre atandı! Şoför ID: {}", driver.getId());
            return shipmentRepository.save(shipment);
        }

        return shipment;
    }





}
