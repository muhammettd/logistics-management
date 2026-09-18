package com.muhammettd.application.repository;

import com.muhammettd.application.model.RouteLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteLogRepository extends MongoRepository<RouteLog, String> {

    List<RouteLog> findByCourierIdOrderByTimestampDesc(String courierId);

}
