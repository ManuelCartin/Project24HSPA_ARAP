/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.controller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author user
 */

@RestController
@RequestMapping("/api/v1/circuit")
public class CircuitController {

    @GetMapping(value = "/spa-geometry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSpaGeometry() {
        try {
            ClassPathResource resource = new ClassPathResource("static/spa_francorchamps.geojson");
            String geoJsonContent = Files.readString(Path.of(resource.getURI()));
            return ResponseEntity.ok(geoJsonContent);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("{\"error\": \"No se pudo cargar la geometría del circuito Spa\"}");
        }
    }
}