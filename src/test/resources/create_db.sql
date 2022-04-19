DROP
ALL OBJECTS;
CREATE SCHEMA IF NOT EXISTS db;

CREATE TABLE country
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    phone_indicative varchar(6)
);

CREATE TABLE city
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    id_country INT,
    FOREIGN KEY (id_country) REFERENCES country(id)
);

CREATE TABLE roles
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(10)
);

CREATE TABLE user
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name  varchar(50),
    first_name varchar(50),
    email      varchar(50),
    password   varchar(250),
    phone_number varchar(15),
    is_verified boolean,
    num_siret char(14),
    company_name varchar(50),
    road_name varchar(250),
    postal_code varchar(10),
    id_city INT,
    id_role INT,
    FOREIGN KEY (id_city) REFERENCES city(id),
    FOREIGN KEY (id_role) REFERENCES roles(id)
);

CREATE TABLE ad
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    image VARCHAR(250),
    text TEXT,
    created_at DATETIME,
    start_date DATE,
    num_days_of_diffusion SMALLINT,
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES user(id)
);

CREATE TABLE time_interval
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    time_slot DOUBLE,
    nbre_ad SMALLINT NULL,
    coef_multi FLOAT
);

CREATE TABLE ad_time_interval
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_ad INT,
    id_time_interval INT,
    FOREIGN KEY (id_ad) REFERENCES ad(id),
    FOREIGN KEY (id_time_interval) REFERENCES time_interval(id)
);

CREATE TABLE area
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    price FLOAT
);

CREATE TABLE ad_area
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_ad INT,
    id_time_interval INT,
    FOREIGN KEY (id_ad) REFERENCES ad(id),
    FOREIGN KEY (id_time_interval) REFERENCES time_interval(id)
);

CREATE TABLE stop
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    latitude FLOAT,
    longitude FLOAT,
    id_area INT,
    adress_ip VARCHAR(15) UNIQUE,
    FOREIGN KEY (id_area) REFERENCES area(id)
);

INSERT INTO country (name, phone_indicative) VALUES
       ( 'France', '+33' ),
       ('Angleterre', '+42');

INSERT INTO city (name, id_country) VALUES
      ( 'Lyon', 1 ),
      ('Paris', 2);

INSERT INTO roles (name) VALUES
   ( 'Admin' ),
   ('User');

INSERT INTO user (last_name, first_name, email, password, phone_number, is_verified, num_siret, company_name, road_name, postal_code, id_city, id_role)
VALUES ( 'admin', 'admin', 'admin@admin.com', 'Admin123', '66666666', true, '14242535652521', 'admin', 'rue admin', '69000', 1, 1);

INSERT INTO time_interval(time_slot, nbre_ad, coef_multi) VALUES
   (6.7, NULL, 1.8),
   (7.8, NULL, 2.4),
   (8.9, NULL, 2.5),
   (9.10, NULL, 1.8),
   (11.12, NULL, 1.2),
   (12.13, NULL, 2.5),
   (13.14, NULL, 2.4),
   (14.15, NULL, 1.4),
   (15.16, NULL, 1.1),
   (16.17, NULL, 1.4),
   (17.18, NULL, 1.7),
   (18.19, NULL, 1.8),
   (19.20, NULL, 1.9);

INSERT INTO area(name, price) VALUES
  ('centre-ville', 45.90 ),
  ('gare', 42.30),
  ('quartier chaud', 10.10);

INSERT INTO stop(name, latitude, longitude, id_area, adress_ip) VALUES
  ( 'centre-ville-1', 45.6584528, 43.98478557, 1, '197.25.65.25' ),
  ( 'centre-ville-2', 45.6584528, 43.98478557, 1, '197.25.65.45' ),
  ( 'centre-ville-3', 45.6584528, 43.98478557, 1, '197.25.65.25' ),
  ( 'centre-ville-4', 45.6584528, 43.98478557, 1, '197.25.65.25' ),
  ( 'centre-ville-5', 45.6584528, 43.98478557, 1, '197.25.65.25' );