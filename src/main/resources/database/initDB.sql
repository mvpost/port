DROP SEQUENCE IF EXISTS id_seq;
CREATE SEQUENCE id_seq START WITH 1 INCREMENT BY 1;

DROP INDEX IF EXISTS country_id_p1;
DROP INDEX IF EXISTS country_name_f1;
DROP INDEX IF EXISTS fish_id_p1;
DROP INDEX IF EXISTS fish_name_f1;
DROP INDEX IF EXISTS jetty_id_p1;
DROP INDEX IF EXISTS jetty_name_f1;

DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS fish;
DROP TABLE IF EXISTS jetty;

CREATE TABLE IF NOT EXISTS country
(
    id    INTEGER PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name  VARCHAR(200) NOT NULL,
    lat  FLOAT NOT NULL,
    lon FLOAT NOT NULL
    );

CREATE UNIQUE INDEX country_id_p1 ON country (id);
CREATE INDEX country_name_f1 ON country (name);

CREATE TABLE IF NOT EXISTS fish
(
    id    INTEGER PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name  VARCHAR(200) NOT NULL,
    price  FLOAT NOT NULL
    );

CREATE UNIQUE INDEX fish_id_p1 ON fish (id);
CREATE INDEX fish_name_f1 ON fish (name);

CREATE TABLE IF NOT EXISTS jetty
(
    id    INTEGER PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name  VARCHAR(200) NOT NULL,
    ships_num SMALLINT NOT NULL,
    capacity INTEGER NOT NULL
    );

CREATE UNIQUE INDEX jetty_id_p1 ON jetty (id);
CREATE INDEX jetty_name_f1 ON jetty (name);