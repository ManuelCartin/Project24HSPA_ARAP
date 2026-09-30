/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.model;
import org.bson.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 *
 * @author user
 */

@org.springframework.data.mongodb.core.mapping.Document(collection = "CircuitGeometry")
public class CircuitGeometry {

    @Id
    private String id;
    private String type;
    private String name;

    // Almacena la lista de features GeoJSON (Points y LineStrings)
    private List<Document> features;

    public CircuitGeometry() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Document> getFeatures() { return features; }
    public void setFeatures(List<Document> features) { this.features = features; }
}