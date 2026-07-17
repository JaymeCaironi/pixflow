CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    pix_key VARCHAR(255) NOT NULL UNIQUE,
    balance NUMERIC(19, 2) NOT NULL
);

CREATE INDEX idx_accounts_pix_key on accounts (pix_key);