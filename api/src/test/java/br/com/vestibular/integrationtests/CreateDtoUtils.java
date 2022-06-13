package br.com.vestibular.integrationtests;

import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

public class CreateDtoUtils {

    public static CreateVestibularDTO createVestibularDto() {
        return new CreateVestibularDTO(LocalDateTime.now(), LocalDateTime.now().plus(1, HOURS));
    }
}
