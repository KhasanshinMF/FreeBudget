DROP TABLE IF EXISTS transaction_categories, transactions, categories, users;

CREATE TABLE IF NOT EXISTS users
(
    id            BIGSERIAL PRIMARY KEY,
    email         VARCHAR(255) NOT NULL UNIQUE,
    hash_password VARCHAR(255) NOT NULL,
    nickname      VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS categories
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users (id),
    name    VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE transactions
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT REFERENCES users (id) NOT NULL,
    amount      DECIMAL(10, 2)               NOT NULL,
    date        DATE                         NOT NULL,
    is_income   BOOLEAN                      NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE transaction_categories
(
    transaction_id BIGINT REFERENCES transactions (id) NOT NULL,
    category_id    BIGINT REFERENCES categories (id)   NOT NULL
);