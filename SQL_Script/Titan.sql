CREATE TABLE CABIN (
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    SHIP_ID INT,
    BED_COUNT INT,
    NAME CHAR(30),
    DECK_LEVEL INT
);

CREATE TABLE CUSTOMER (
   ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   FIRSTNAME VARCHAR(255),
   LASTNAME VARCHAR(255)
)