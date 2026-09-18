package com.muhammettd.application.service;

import com.muhammettd.application.dto.ShipmentCreateRequest;
import com.muhammettd.application.dto.ShipmentResponse;
import com.muhammettd.application.model.Customer;
import com.muhammettd.application.model.Shipment;
import com.muhammettd.application.model.ShipmentStatus;
import com.muhammettd.application.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShipmentService {

    private static final Logger log = LoggerFactory.getLogger(ShipmentService.class);


    private final CustomerRepository customerRepository;
    private final DispatchService dispatchService;


    public ShipmentService(CustomerRepository customerRepository, DispatchService dispatchService) {

        this.customerRepository = customerRepository;
        this.dispatchService = dispatchService;
    }

    public ShipmentResponse createAndDispatch(ShipmentCreateRequest request) {

        Customer sender = customerRepository.findById(request.senderId())
                .orElseThrow(() -> new RuntimeException("Gönderici bulunamadı!"));

        Customer receiver = customerRepository.findById(request.receiverId())
                .orElseThrow(() -> new RuntimeException("Alıcı bulunamadı"));

        Shipment shipment = new Shipment(
                sender,
                receiver,
                null,
                request.pickupLatitude(),
                request.pickupLongitude(),
                request.deliveryLatitude(),
                request.deliveryLongitude(),
                request.totalAmount(),
                ShipmentStatus.CREATED,
                LocalDateTime.now(),
                null,
                request.weightKg()
        );

        Shipment dispatchedShipment = dispatchService.dispatchShipmentToNearestDriver(shipment);

        ShipmentResponse shipmentResponse = new ShipmentResponse();
        shipmentResponse.setShipmentId(dispatchedShipment.getId());
        shipmentResponse.setStatus(dispatchedShipment.getShipmentStatus());
        shipmentResponse.setAssignedDriverId(dispatchedShipment.getId());
        shipmentResponse.setMessage("Kargo işlemi tamamlandı.");

        return shipmentResponse;

    }

}
