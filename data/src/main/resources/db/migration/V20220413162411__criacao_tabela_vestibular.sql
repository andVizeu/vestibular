CREATE TABLE IF NOT EXISTS vestibular (
    id                      BIGSERIAL PRIMARY KEY,
    vestibular_uuid UUID    NOT NULL DEFAULT gen_random_uuid(),
    data_inicio             TIMESTAMP NOT NULL,
    data_fim                TIMESTAMP NOT NULL,
    created_at              TIMESTAMP NOT NULL DEFAULT now(),
    updated_at              TIMESTAMP NOT NULL DEFAULT now(),
    version                 BIGINT
);