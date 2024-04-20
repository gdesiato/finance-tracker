DROP TABLE IF EXISTS transaction_entry CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE transaction_entry (
    id SERIAL PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    category_id BIGINT,
    date DATE NOT NULL,
    description VARCHAR(255),
    CONSTRAINT fk_category
        FOREIGN KEY(category_id)
        REFERENCES category(id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
