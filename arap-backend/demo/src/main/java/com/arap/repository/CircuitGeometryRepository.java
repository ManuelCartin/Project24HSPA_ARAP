/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arap.repository;
import com.arap.model.CircuitGeometry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author user
 */

import java.util.Optional;

@Repository
public interface CircuitGeometryRepository extends MongoRepository<CircuitGeometry, String> {

    Optional<CircuitGeometry> findByName(String name);

}