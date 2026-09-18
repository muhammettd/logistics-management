package com.muhammettd.application.repository;

import com.muhammettd.application.model.Shipment;
import com.muhammettd.application.model.ShipmentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends MongoRepository<Shipment, String> {

    List<Shipment> findBySenderId(String senderId);

    // 2. Bir müşterinin "Teslim Alacağı" tüm kargoları bulmak için
    List<Shipment> findByReceiverId(String receiverId);

    // 3. Bir şoföre (Driver) atanmış kargoları bulmak için (İleride lazım olacak)
    List<Shipment> findByDriverId(String driverId);

}
