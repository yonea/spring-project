CREATE TABLE registrant (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    lastname VARCHAR(50) NOT NULL,
    firstname VARCHAR(50),
    age INTEGER NOT NULL,
    country VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);