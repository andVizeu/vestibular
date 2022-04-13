CREATE TABLE IF NOT EXISTS curso (
    id                      BIGSERIAL PRIMARY KEY,
    curso_uuid UUID         NOT NULL DEFAULT gen_random_uuid(),
    nome                    VARCHAR(255) NOT NULL,
    created_at              TIMESTAMP NOT NULL DEFAULT now(),
    updated_at              TIMESTAMP NOT NULL DEFAULT now(),
    version                 BIGINT
);

CREATE TABLE IF NOT EXISTS vestibular_curso (
    id                      BIGSERIAL NOT NULL,
    vestibular_id           BIGINT NOT NULL,
    curso_id                BIGINT NOT NULL,
    created_at              TIMESTAMP NOT NULL DEFAULT now(),
    updated_at              TIMESTAMP NOT NULL DEFAULT now(),
    version                 BIGINT,
    CONSTRAINT vestibularidvestibularcursofk FOREIGN KEY (vestibular_id) REFERENCES vestibular,
    CONSTRAINT cursoidvestibularcursofk FOREIGN KEY (curso_id) REFERENCES  curso
);