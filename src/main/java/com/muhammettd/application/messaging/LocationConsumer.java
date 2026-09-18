package com.muhammettd.application.messaging;

import com.muhammettd.application.dto.LocationEvent;
import com.muhammettd.application.model.RouteLog;
import com.muhammettd.application.repository.RouteLogRepository;
import com.muhammettd.application.service.RedisLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LocationConsumer {

    private static final Logger log = LoggerFactory.getLogger(LocationConsumer.class);

    private final RedisLocationService redisLocationService;
    private final RouteLogRepository routeLogRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public LocationConsumer(RedisLocationService redisLocationService, RouteLogRepository routeLogRepository, SimpMessagingTemplate messagingTemplate) {
        this.redisLocationService = redisLocationService;
        this.routeLogRepository = routeLogRepository;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * @KafkaListener, Spring'in Kafka kuyruğunu sürekli dinlemesini sağlar.
     * fleet.driver.location kuyruğuna mesaj düştüğü an bu metot tetiklenir.
     */

    @KafkaListener(topics = "fleet.driver.location", groupId = "fleet-consumer-group")
    public void consumeLocationEvent(LocationEvent event) {
        log.debug("Kafkadan yakalandı. Kurye {}", event.getDriverId());

        try {
            redisLocationService.updateCourierLocation(
                    event.getDriverId(),
                    event.getLongitude(),
                    event.getLatitude()
            );

            RouteLog routeLog = new RouteLog();
            routeLog.setCourierId(event.getDriverId());
            routeLog.setLocation(new GeoJsonPoint(event.getLongitude(), event.getLatitude()));
            routeLog.setSpeed(event.getSpeed());
            routeLog.setHeading(event.getHeading());
            routeLog.setTimestamp(event.getTimestamp() > 0 ? Instant.ofEpochMilli(event.getTimestamp()) : Instant.now());

            routeLogRepository.save(routeLog);

            messagingTemplate.convertAndSend("/topic/courier-location", event);

            log.info("WebSocket'e fırtaıldı: Kurye {} -> [{},{}]", event.getDriverId(), event.getLongitude(), event.getLatitude());

        } catch (Exception e) {
            log.error("Kafka mesajı işlenirken hata oluştu! Kurye: {}", event.getDriverId(), e);
        }
    }

}
