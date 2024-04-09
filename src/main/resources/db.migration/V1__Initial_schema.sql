-- Drop the tables if they exist to avoid conflicts
DROP TABLE IF EXISTS transaction_entry CASCADE;
DROP TABLE IF EXISTS category CASCADE;

-- Create the category table
CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the transaction_entry table
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