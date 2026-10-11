CREATE DATABASE ARAP_SQL;
USE ARAP_SQL

CREATE TABLE circuit_spec (
    circuit_id INT IDENTITY(1,1) PRIMARY KEY,
    circuit_name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    country VARCHAR(100),
    length_km DECIMAL(6,3),
    corners INT,
    pit_boxes INT
);

INSERT INTO circuit_spec
(circuit_name, location, country, length_km, corners, pit_boxes)
VALUES
('Circuit de Spa-Francorchamps',
 'Stavelot',
 'Belgium',
 7.004,
 20,
 35);

 -- =========================================
-- VEHICLE
-- =========================================

CREATE TABLE Vehicle (
    Vehicle_id INT IDENTITY(1,1) PRIMARY KEY,
    Category VARCHAR(50) NOT NULL,
    VehicleName VARCHAR(100) NOT NULL
);


-- =========================================
-- DRIVERS
-- =========================================

CREATE TABLE Drivers (
    Driver_id INT IDENTITY(1,1) PRIMARY KEY,
    DriverName VARCHAR(100) NOT NULL,
    DriverLastName VARCHAR(100) NOT NULL,
    DriverStatus VARCHAR(50)
);


-- =========================================
-- DRIVER LINEUP
-- =========================================

CREATE TABLE Driver_Lineup (
    Team_Driver_id INT IDENTITY(1,1) PRIMARY KEY,
    Team_id INT NOT NULL,
    Driver_id INT NOT NULL
);


-- =========================================
-- TEAMS
-- =========================================

CREATE TABLE Teams (
    Team_id INT IDENTITY(1,1) PRIMARY KEY,
    TeamName VARCHAR(100) NOT NULL,
    TeamCountry VARCHAR(100),
    Car_Number INT,
    Vehicle_id INT NOT NULL,
    Team_Driver_id INT
);


-- =========================================
-- RACE RESULT
-- =========================================

CREATE TABLE RaceResult (
    RaceResult_id INT IDENTITY(1,1) PRIMARY KEY,
    Team_Driver_id INT NOT NULL,
    Circuit_id INT NOT NULL,
    edition INT NOT NULL,
    position INT,
    laps INT,
    gap VARCHAR(50),
    stint_reliability_issue VARCHAR(255)
);

ALTER TABLE Vehicle
ADD VehicleCode VARCHAR(50) NOT NULL;

SELECT * FROM Vehicle;

INSERT INTO Vehicle
(VehicleCode, Category, VehicleName)
VALUES
('GT3_001', 'GT3', 'Audi R8 R8 LMS GT3'),
('GT2_001', 'GT2', 'Audi R8 LMS GT2'),
('GT4_001', 'GT4', 'Audi R8 LMS GT4'),
('ROAD_001', 'ROAD', 'Audi R8 V10 Performance Quattro');

ALTER TABLE Drivers
ADD DriverCode VARCHAR(50) NOT NULL;

INSERT INTO Drivers
(DriverCode, DriverName, DriverLastName, DriverStatus)
VALUES
('DRIVER_001', 'Simon', 'Reicher', 'confirmed'),
('DRIVER_002', 'Markus', 'Winkelhock', 'confirmed'),
('DRIVER_003', 'Christopher', 'Haase', 'confirmed'),
('DRIVER_004', 'Simon', 'Balcaen', 'confirmed'),
('DRIVER_005', 'Mathieu', 'Castelein', 'confirmed'),
('DRIVER_006', 'Pierre', 'Castelein', 'confirmed'),
('DRIVER_007', 'Steven', 'Palette', 'confirmed'),
('DRIVER_008', 'Carrie', 'Schreiner', 'confirmed'),
('DRIVER_009', 'Georgios', 'Kolovos', 'confirmed'),
('DRIVER_010', 'Daniele', 'Di Amato', 'confirmed'),
('DRIVER_010', 'Sergio', 'Sette Camara', 'confirmed'),
('DRIVER_011', 'Ezequiel', 'Perez Companc', 'confirmed'),
('DRIVER_012', 'Etienne', 'Cheli', 'confirmed'),
('DRIVER_013', 'Ivan', 'Klymenko', 'confirmed'),
('DRIVER_014', 'Lucas', 'Legeret', 'confirmed');


ALTER TABLE Teams
ADD CONSTRAINT FK_Teams_Vehicle
FOREIGN KEY (Vehicle_id)
REFERENCES Vehicle(Vehicle_id);

ALTER TABLE Teams
ADD CONSTRAINT FK_Teams_DriverLineup
FOREIGN KEY (Team_Driver_id)
REFERENCES Driver_Lineup(Team_Driver_id);

INSERT INTO Teams
(TeamName, TeamCountry, Car_Number, Vehicle_id)
VALUES
('Eastalent Racing', NULL, 84, 1),
('Haas RT', NULL, 28, 1),
('Tresor Attempto Racing', NULL, 88, 1),
('Saintéloc Racing', NULL, 25, 1);

SELECT * FROM Teams;

INSERT INTO Driver_Lineup
(Team_id, Driver_id)
VALUES
-- Eastalent Racing #84
(1, 1),
(1, 2),
(1, 3),

-- Haas RT #28
(2, 4),
(2, 5),
(2, 6),
(2, 7),

-- Tresor Attempto Racing #88
(3, 8),
(3, 9),
(3, 10),
(3, 11),

-- Saintéloc Racing #25
(4, 12),
(4, 13),
(4, 14),
(4, 15);

SELECT *
FROM Driver_Lineup;\

ALTER TABLE RaceResult
ADD CONSTRAINT FK_RaceResult_Circuit
FOREIGN KEY (Circuit_id)
REFERENCES circuit_spec(circuit_id);

INSERT INTO RaceResult
(Team_Driver_id, Circuit_id, edition, position, laps, gap, stint_reliability_issue)
VALUES
(1, 1, 2026, NULL, 233, '-13:16:53.726', 'Puncture, accident'),
(5, 1, 2026, NULL, 69, '-20:11:58.604', 'Beached'),
(9, 1, 2026, NULL, 179, '-15:27:54.860', 'Engine'),
(12, 1, 2026, 30, 531, '53.563', 'NA/ Finished');

SELECT * FROM RaceResult;