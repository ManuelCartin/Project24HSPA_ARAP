/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Map;
/**
 *
 * @author user
 */



@Service
public class StintAnalysisService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Map getStintSummary(String sessionId) {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("sessionId").is(sessionId)),
            Aggregation.group("sessionId")
                .avg("speed_kmh").as("avgSpeedKmh")
                .max("speed_kmh").as("topSpeedKmh")
                .min("lap_time_seconds").as("bestLapTime")
                .avg("lap_time_seconds").as("avgLapTime")
                .max("lap_number").as("totalLapsCompleted")
        );

        AggregationResults<Map> results = mongoTemplate.aggregate(
            aggregation, "Telemetry_Audi_GT3", Map.class
        );

        return results.getUniqueMappedResult();
    }
}