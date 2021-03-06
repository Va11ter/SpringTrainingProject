/*Tested on Mysql 8.0*/

CREATE DATABASE IF NOT EXISTS book_store;
USE book_store;

CREATE TABLE IF NOT EXISTS address(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    postal_code VARCHAR(10),
    country VARCHAR(60),
    city VARCHAR(85),
    street VARCHAR(100),
    building VARCHAR(25),
    apartment VARCHAR(25),
    info varchar(256)
);

CREATE TABLE IF NOT EXISTS person(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50),
    address_id INT,
    bonus INT DEFAULT 0,
    password VARCHAR(128) NOT NULL,
    role varchar(10),

    CONSTRAINT uniq_email UNIQUE (email),

    CONSTRAINT
        FOREIGN KEY (address_id)
            REFERENCES address (id)
            ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS item(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL,
    description TEXT,
    count INTEGER
);

CREATE TABLE IF NOT EXISTS `order`(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    person_id INT NOT NULL,
    address_id INT,
    state VARCHAR(10),
    total DECIMAL,
    placed_on timestamp,
    used_bonuses INT default 0,
    earned_bonuses INT default 0,

    INDEX (person_id),
    FOREIGN KEY (person_id)
        REFERENCES person(id),

    FOREIGN KEY (address_id)
        REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS order_item(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    item_price DECIMAL,
    item_count INT default 1,

    INDEX (order_id, item_id),
    INDEX (order_id),

    FOREIGN KEY (order_id)
        REFERENCES `order` (id)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    FOREIGN KEY (item_id)
        REFERENCES item(id)
);

CREATE TABLE IF NOT EXISTS promo(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(25),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    is_active boolean default true,
    discount SMALLINT,
    description TEXT,
    type VARCHAR(15),
    person_id INT,

    CONSTRAINT unique_code UNIQUE (code),

    FOREIGN KEY (person_id)
        REFERENCES person(id)
);

CREATE TABLE IF NOT EXISTS wish_list(
    person_id INT,
    item_id INT,

    primary key (person_id, item_id),
    FOREIGN KEY (person_id)
        REFERENCES person(id),
    FOREIGN KEY (item_id)
        REFERENCES item(id)
);

CREATE TABLE IF NOT EXISTS cart(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    person_id INT,
    promo_id INT,
    applied_bonuses INT default 0,

    FOREIGN KEY (person_id) REFERENCES
        person(id),

    FOREIGN KEY (promo_id) REFERENCES
        promo(id),

    CONSTRAINT unique_person_id UNIQUE (person_id)
);

CREATE TABLE IF NOT EXISTS cart_item (
    no INT NOT NULL AUTO_INCREMENT,
    cart_id INT NOT NULL,
    item_id INT NOT NULL,
    count INT default 1,

    PRIMARY KEY (`no`),
    INDEX (cart_id, item_id),
    INDEX (item_id),

    FOREIGN KEY (cart_id)
        REFERENCES cart (id)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    FOREIGN KEY (item_id)
        REFERENCES item(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS category(
    id INT NOT NULL AUTO_INCREMENT primary key,
    name VARCHAR(128),

    INDEX (name)
);


CREATE TABLE IF NOT EXISTS item_category(
     no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     item_id INT NOT NULL,
     category_id INT NOT NULL,

     INDEX (item_id, category_id),

     FOREIGN KEY (item_id)
         REFERENCES item(id)
         ON UPDATE CASCADE ON DELETE RESTRICT,

     FOREIGN KEY (category_id)
         REFERENCES category(id)
         ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS promo_category(
    no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    promo_id INT NOT NULL,
    category_id INT NOT NULL,

    INDEX (promo_id, category_id),

    FOREIGN KEY (promo_id)
        REFERENCES promo(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,

    FOREIGN KEY (category_id)
        REFERENCES category(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
)


