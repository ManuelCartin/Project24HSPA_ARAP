/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.service;
import com.arap.dto.SectorLengthDTO;
import com.arap.model.CircuitGeometry;
import com.arap.repository.CircuitGeometryRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @author user
 */

@Service
public class CircuitGeometryService {

    private static final double EARTH_RADIUS_METERS = 6371000.0;

    @Autowired
    private CircuitGeometryRepository repository;

    public CircuitGeometry getCircuitByName(String circuitName) {
        return repository.findByName(circuitName).orElse(null);
    }

    public SectorLengthDTO getSectorLength(String circuitName, String sectorName) {
        CircuitGeometry circuit = getCircuitByName(circuitName);
        if (circuit == null || circuit.getFeatures() == null) return null;

        for (Document feature : circuit.getFeatures()) {
            Document properties = (Document) feature.get("properties");
            if (properties != null && sectorName.equalsIgnoreCase(properties.getString("name"))) {
                Document geometry = (Document) feature.get("geometry");
                List<List<Double>> coordinates = (List<List<Double>>) geometry.get("coordinates");

                double meters = calculateLineStringLength(coordinates);
                return new SectorLengthDTO(circuitName, sectorName, meters);
            }
        }
        return null;
    }

    private double calculateLineStringLength(List<List<Double>> coordinates) {
        double totalDistance = 0.0;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            List<Double> p1 = coordinates.get(i);
            List<Double> p2 = coordinates.get(i + 1);
            totalDistance += haversine(p1.get(1), p1.get(0), p2.get(1), p2.get(0));
        }
        return totalDistance;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_METERS * c;
    }
}