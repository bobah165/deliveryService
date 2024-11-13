--DROP SCHEMA IF EXISTS general CASCADE;

CREATE SCHEMA IF NOT EXISTS general;

--UTILS SCHEMA

CREATE TABLE general.delivery
(
    id varchar    NOT NULL,
    is_exist boolean  NOT NULL        DEFAULT FALSE,
    name varchar NOT NULL,
    CONSTRAINT pk_delivery
        PRIMARY KEY (id)
);


