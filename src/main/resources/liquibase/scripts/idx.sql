--liquibase formatted sql

--changeset jrembo:1
CREATE INDEX name_idx
ON student (name);

CREATE INDEX name_and_color_idx
ON faculty (name, color);