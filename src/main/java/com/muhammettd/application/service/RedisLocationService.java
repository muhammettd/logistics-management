package com.muhammettd.application.service;

import com.muhammettd.application.dto.GeoResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisLocationService {

    private static final Logger log = LoggerFactory.getLogger(RedisLocationService.class);

    private static final String ACTIVE_COURIERS_KEY = "fleet:active_couriers";

    private final StringRedisTemplate redisTemplate;

    public RedisLocationService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 1. Kuryenin Konumunu Redis'e Ekleme veya Güncelleme (GEOADD)
     * Bu metot Kafka'dan her saniye akacak veriyle tetiklenecek.
     */

    public void updateCourierLocation(String courierId, double longitude, double latitude) {
        log.debug("Redis güncelliyor: Kuryer {} -> [{}, {}]", courierId, longitude, latitude);

        // GEOADD komutu: Eğer kurye havuzda yoksa ekler, varsa sadece konumunu günceller. (O(log(N)) karmaşıklığı ile çok hızlıdır)
        redisTemplate.opsForGeo().add(
                ACTIVE_COURIERS_KEY,
                new Point(longitude, latitude), // Önce boylam x sonra enlem y
                courierId
        );

    }

    public void removeCourierLocation(String courierId) {
        log.info("Kurye çevrımdışı, Redis haritasından siliniyor: {}", courierId);

        // Arka planda Geo yapıları "Sorted Set" (ZSET) olduğu için ZREM (opsForZSet().remove) komutu ile silinir.
        redisTemplate.opsForZSet().remove(ACTIVE_COURIERS_KEY, courierId);

    }

    /**
     * 3. Belirli Bir Adrese En Yakın Kuryeleri Bulma (GEOSEARCH)
     * Bu metot Sipariş modülünde "En Yakın Kurye Aranıyor" kısmında kullanılacak.
     *
     * @param longitude Hedef (Müşteri) boylam
     * @param latitude Hedef (Müşteri) enlem
     * @param radiusKm Arama çapı (Örn: 3 KM)
     * @return En yakın kuryelerin ID'leri ve mesafeleri listesi
     */

    public String findNearbyDrivers(double longitude, double latitude, double radiusKm) {
        log.info("[{}, {}] koordinatının {} KM çapındaki kuryeler aranıyor...", longitude, latitude, radiusKm);

        Distance radius = new Distance(radiusKm, Metrics.KILOMETERS);

        Circle searchArea = new Circle(new Point(longitude, latitude), radius);

        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .sortAscending()
                .limit(1);

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
                .radius(ACTIVE_COURIERS_KEY, searchArea, args);

        if (results != null && !results.getContent().isEmpty()) {
            return results.getContent().get(0).getContent().getName();
        }

        return null;

    }






}
