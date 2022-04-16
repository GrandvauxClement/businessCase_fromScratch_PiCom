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

CREATE TABLE role
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
    FOREIGN KEY (id_role) REFERENCES role(id)
);

INSERT INTO country (name, phone_indicative) VALUES
       ( 'France', '+33' ),
       ('Angleterre', '+42');

INSERT INTO city (name, id_country) VALUES
      ( 'Lyon', 1 ),
      ('Paris', 2);

INSERT INTO role (name) VALUES
   ( 'Admin' ),
   ('User');

INSERT INTO user (last_name, first_name, email, password, phone_number, is_verified, num_siret, company_name, road_name, postal_code, id_city, id_role)
VALUES ( 'admin', 'admin', 'admin@admin.com', 'Admin123', '66666666', true, '14242535652521', 'admin', 'rue admin', '69000', 1, 1);