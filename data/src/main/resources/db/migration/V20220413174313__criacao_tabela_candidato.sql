CREATE TABLE IF NOT EXISTS candidato (
    id                      BIGSERIAL PRIMARY KEY,
    vestibular_id           BIGINT NOT NULL,
    nome                    VARCHAR(255) NOT NULL,
    data_nascimento         timestamp NOT NULL,
    cpf                     VARCHAR(25) NOT NULL,
    curso_id                BIGINT NOT NULL,
    sala_id                 BIGINT,
    created_at              TIMESTAMP NOT NULL DEFAULT now(),
    updated_at              TIMESTAMP NOT NULL DEFAULT now(),
    version                 BIGINT,
    CONSTRAINT cursoidcandidatofk FOREIGN KEY (curso_id) REFERENCES curso,
    CONSTRAINT salaidcandidatofk FOREIGN KEY (sala_id) REFERENCES sala,
    CONSTRAINT vestibularidcandidatofk FOREIGN KEY (vestibular_id) REFERENCES vestibular
);