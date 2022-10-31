DROP TABLE IF EXISTS player;

CREATE TABLE player (
    id SERIAL PRIMARY KEY,
--     name VARCHAR(50),
    hp integer,
    x integer,
    y integer
)
