/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */



@RestController
@RequestMapping("/api/v1/circuit")
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend web
public class CircuitController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping(value = "/spa-geometry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSpaGeometry() {
        Query query = new Query(Criteria.where("name").is("Circuit of Spa-Francorchamps"));
        Document circuit = mongoTemplate.findOne(query, Document.class, "CircuitGeometry");

        if (circuit != null) {
            // Remueve el _id de MongoDB si no deseas enviarlo al cliente GeoJSON estándar
            circuit.remove("_id");
            return ResponseEntity.ok(circuit.toJson());
        }

        return ResponseEntity.status(404)
                .body("{\"error\": \"No se encontró la geometría en la base de datos\"}");
    }
}