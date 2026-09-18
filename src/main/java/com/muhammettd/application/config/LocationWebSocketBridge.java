package com.muhammettd.application.config;

import com.muhammettd.application.dto.LocationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class LocationWebSocketBridge {

    private final SimpMessagingTemplate messagingTemplate;


    public LocationWebSocketBridge(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "driver-location-stream", groupId = "websocket-broadcaster-group")
    public void broadcastToFrontend(LocationEvent event) {
        // Gelen Kafka mesajını doğrudan "/topic/couriers" kanalına fırlat
        messagingTemplate.convertAndSend("/topic/couriers", event);
    }

    // Önemli Not: Eğer sadece belirli bir siparişi izleyen kullanıcıya
    // veri göndermek istersen kanalı dinamik yapabilirsin:
    // messagingTemplate.convertAndSend("/topic/order/" + event.getActiveOrderId(), event);

}
