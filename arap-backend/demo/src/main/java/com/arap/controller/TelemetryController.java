/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.controller;
import com.arap.model.TelemetryGT3;
import com.arap.repository.TelemetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author user
 */


@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {

    @Autowired
    private TelemetryRepository telemetryRepository;

    // Endpoint para obtener la serie de tiempo completa de un stint por sessionId
    @GetMapping("/gt3/{sessionId}")
    public ResponseEntity<List<TelemetryGT3>> getStintData(@PathVariable String sessionId) {
        List<TelemetryGT3> data = telemetryRepository.findBySessionIdOrderByTimestampAsc(sessionId);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }
}
