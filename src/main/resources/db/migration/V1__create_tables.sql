CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE tb_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tb_parties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date TIMESTAMP NOT NULL,
    local VARCHAR(255) NOT NULL,
    description TEXT,
    theme VARCHAR(255),
    dress_code VARCHAR(255),
    price DOUBLE PRECISION,
    user_id BIGINT NOT NULL REFERENCES tb_users(id)
);

CREATE TABLE tb_participants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    telefone TEXT NOT NULL,
    owner BOOLEAN NOT NULL DEFAULT FALSE,
    sorted BOOLEAN DEFAULT FALSE,
    name_of_sorted_participant VARCHAR(255),
    user_id BIGINT NOT NULL REFERENCES tb_users(id),
    party_id BIGINT NOT NULL REFERENCES tb_parties(id),
    confirmed BOOLEAN DEFAULT FALSE,
    responsible_for_another_participant BOOLEAN DEFAULT FALSE
);

CREATE TABLE tb_participants_without_wpp (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    confirmed BOOLEAN DEFAULT FALSE,
    sorted BOOLEAN DEFAULT FALSE,
    participant_id BIGINT NOT NULL REFERENCES tb_participants(id)
);

CREATE TYPE consumable_type AS ENUM ('FOOD', 'DRINK');

CREATE TABLE tb_consumable_items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    participant_id BIGINT REFERENCES tb_participants(id),
    participant_without_wpp_id BIGINT REFERENCES tb_participants_without_wpp(id),
    type consumable_type NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE tb_gifts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    participant_id BIGINT NOT NULL REFERENCES tb_participants(id),
    participant_without_wpp_id BIGINT NOT NULL REFERENCES tb_participants_without_wpp(id)
);
