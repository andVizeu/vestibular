package br.com.vestibular.integrationtests;

import br.com.vestibular.api.entrypoint.rest.curso.requests.CreateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.curso.requests.UpdateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.sala.requests.CreateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.requests.UpdateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.UpdateVestibularDTO;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.WEEKS;

public class CreateDtoUtils {

    public static CreateVestibularDTO createVestibularDTO() {
        return new CreateVestibularDTO(LocalDateTime.now(), LocalDateTime.now().plus(1, HOURS));
    }

    public static UpdateVestibularDTO updateVestibularDTO() {
        return new UpdateVestibularDTO(LocalDateTime.now(), LocalDateTime.now().plus(1, WEEKS));
    }

    public static CreateCursoDTO createCursoDTO() {
        return new CreateCursoDTO("Curso");
    }

    public static UpdateCursoDTO updateCursoDTO() {
        return new UpdateCursoDTO("Curso atualizado");
    }

    public static CreateSalaDTO createSalaDTO() {
        return new CreateSalaDTO("A101", "A", 30);
    }

    public static UpdateSalaDTO updateSalaDTO() {
        return new UpdateSalaDTO("B101", "B", 40);
    }

}
