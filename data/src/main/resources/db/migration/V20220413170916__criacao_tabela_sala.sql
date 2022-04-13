CREATE TABLE IF NOT EXISTS sala (
    id                      BIGSERIAL PRIMARY KEY,
    vestibular_uuid         UUID NOT NULL,
    curso_uuid              UUID NOT NULL,
    identificador           VARCHAR(5) NOT NULL,
    bloco                   VARCHAR(5) NOT NULL,
    capacidade              BIGINT NOT NULL,
    created_at              TIMESTAMP NOT NULL DEFAULT now(),
    updated_at              TIMESTAMP NOT NULL DEFAULT now(),
    version                 BIGINT
);