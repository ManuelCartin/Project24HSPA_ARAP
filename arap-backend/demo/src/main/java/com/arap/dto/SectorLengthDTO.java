/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.dto;

/**
 *
 * @author user
 */
public class SectorLengthDTO {

    private String circuitName;
    private String sectorName;
    private double lengthMeters;
    private double lengthKm;

    public SectorLengthDTO(String circuitName, String sectorName, double lengthMeters) {
        this.circuitName = circuitName;
        this.sectorName = sectorName;
        this.lengthMeters = lengthMeters;
        this.lengthKm = lengthMeters / 1000.0;
    }

    public String getCircuitName() { return circuitName; }
    public String getSectorName() { return sectorName; }
    public double getLengthMeters() { return lengthMeters; }
    public double getLengthKm() { return lengthKm; }
}