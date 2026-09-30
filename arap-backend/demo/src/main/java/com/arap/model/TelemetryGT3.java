/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
/**
 *
 * @author user
 */

@Document(collection = "Telemetry_Audi_GT3")
public class TelemetryGT3 {

    @Id
    private String id;

    @Field("sessionId")
    private String sessionId;

    @Field("timestamp")
    private Date timestamp;

    @Field("speed_kmh")
    private Double speedKmh;

    @Field("engine_rpm")
    private Integer engineRpm;

    @Field("gear")
    private Integer gear;

    @Field("fuel_level_liters")
    private Double fuelLevelLiters;

    @Field("lap_number")
    private Integer lapNumber;

    @Field("lap_time_seconds")
    private Double lapTimeSeconds;

    // Constructores
    public TelemetryGT3() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public Double getSpeedKmh() { return speedKmh; }
    public void setSpeedKmh(Double speedKmh) { this.speedKmh = speedKmh; }

    public Integer getEngineRpm() { return engineRpm; }
    public void setEngineRpm(Integer engineRpm) { this.engineRpm = engineRpm; }

    public Integer getGear() { return gear; }
    public void setGear(Integer gear) { this.gear = gear; }

    public Double getFuelLevelLiters() { return fuelLevelLiters; }
    public void setFuelLevelLiters(Double fuelLevelLiters) { this.fuelLevelLiters = fuelLevelLiters; }

    public Integer getLapNumber() { return lapNumber; }
    public void setLapNumber(Integer lapNumber) { this.lapNumber = lapNumber; }

    public Double getLapTimeSeconds() { return lapTimeSeconds; }
    public void setLapTimeSeconds(Double lapTimeSeconds) { this.lapTimeSeconds = lapTimeSeconds; }
}