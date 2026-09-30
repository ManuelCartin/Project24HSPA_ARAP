/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.repository;
import com.arap.model.TelemetryGT3;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *
 * @author user
 */


@Repository
public interface TelemetryRepository extends MongoRepository<TelemetryGT3, String> {
    
    // Buscar telemetría ordenada por tiempo para graficar la serie temporal
    List<TelemetryGT3> findBySessionIdOrderByTimestampAsc(String sessionId);
}
