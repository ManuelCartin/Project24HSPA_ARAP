# ARAP — Audi Racing Analysis Platform

## 24 Hours of Spa — NoSQL Project

ARAP is a web-based racing analysis platform developed as part
of a NoSQL academic project.

The platform integrates racing telemetry, weather conditions,
circuit geometry and vehicle performance data related to the
24 Hours of Spa-Francorchamps.

## Technologies

- MongoDB
- Spring Boot
- Java
- REST API
- HTML5
- CSS3
- JavaScript
- GeoJSON
- WebGIS

## Architecture

```text
MongoDB
   │
   ▼
Spring Boot REST API
   │
   ▼
ARAP Web Frontend
   │
   ├── Telemetry
   ├── Weather
   ├── Circuit Geometry
   └── Performance Analysis