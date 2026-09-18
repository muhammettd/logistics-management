package com.muhammettd.application.messaging;

import com.muhammettd.application.dto.LocationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationProducer {

    private static final Logger log = LoggerFactory.getLogger(LocationProducer.class);
    private static final String TOPIC = "fleet.driver.location";

    private final KafkaTemplate<String, LocationEvent> kafkaTemplate;

    public LocationProducer(KafkaTemplate<String, LocationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishLocation(LocationEvent event) {
        log.debug("Kafkaya gönderiliyor. Kurye {} -> [{},{}]", event.getDriverId(), event.getLongitude());

        /*
         * Kritik Detay: Kafka'da mesaj gönderirken 'Anahtar' (Key) olarak kurye ID'sini veriyoruz.
         * Neden? Kafka aynı Key'e sahip mesajları hep aynı bölüme (Partition) yollar.
         * Böylece Ahmet kuryenin 1. saniyedeki ve 2. saniyedeki konumları asla karışmaz,
         * veriler sırasıyla işlenir! (Ordering Guarantee)
         */

        kafkaTemplate.send(TOPIC, event.getDriverId(), event);

    }


}
