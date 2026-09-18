package com.muhammettd.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;

@Document(collection = "route_logs")
// 1. İndeks: Belirli bir kuryenin geçmiş rotasını kronolojik olarak saniyeler içinde çekmek için.
@CompoundIndex(name = "courier_time_idx", def = "{'courierId': 1, 'timestamp': -1}" )
public class RouteLog implements Serializable {

    @Id
    private String id;

    private String courierId;

    // İşin sırrı: Bu yapı sayesinde "Kadıköy bölgesinden geçen kuryeleri getir" sorgusu atılabilir.
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    private double speed;

    private double heading;

    private Instant timestamp;

    private String activeOrderId;

    public RouteLog() {
    }

    public RouteLog(String id, String couierId, GeoJsonPoint location, double speed, double heading, Instant timestamp, String activeOrderId) {
        this.id = id;
        this.courierId = couierId;
        this.location = location;
        this.speed = speed;
        this.heading = heading;
        this.timestamp = timestamp;
        this.activeOrderId = activeOrderId;
    }

    public String getId() {
        return id;
    }

    public String getCourierId() {
        return courierId;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public double getSpeed() {
        return speed;
    }

    public double getHeading() {
        return heading;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getActiveOrderId() {
        return activeOrderId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setActiveOrderId(String activeOrderId) {
        this.activeOrderId = activeOrderId;
    }
}
