CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    origin_key VARCHAR(255) NOT NULL,
    destination_key VARCHAR(255) NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);