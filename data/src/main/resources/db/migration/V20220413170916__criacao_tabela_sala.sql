CREATE TABLE IF NOT EXISTS sala (
    id                      BIGSERIAL PRIMARY KEY,
    vestibular_id           BIGINT NOT NULL,
    curso_uuid              UUID,
    identificador           VARCHAR(5) NOT NULL,
    bloco                   VARCHAR(5) NOT NULL,
    capacidade              BIGINT NOT NULL,
    created_at              TIMESTAMP NOT NULL DEFAULT now(),
    updated_at              TIMESTAMP NOT NULL DEFAULT now(),
    version                 BIGINT,
    CONSTRAINT salaidvestibularfk FOREIGN KEY (vestibular_id) REFERENCES vestibular
);