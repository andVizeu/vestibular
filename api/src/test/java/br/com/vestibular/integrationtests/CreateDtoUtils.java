package br.com.vestibular.integrationtests;

import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.UpdateVestibularDTO;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.WEEKS;

public class CreateDtoUtils {

    public static CreateVestibularDTO createVestibularDto() {
        return new CreateVestibularDTO(LocalDateTime.now(), LocalDateTime.now().plus(1, HOURS));
    }

    public static UpdateVestibularDTO updateVestibularDTO() {
        return new UpdateVestibularDTO(LocalDateTime.now(), LocalDateTime.now().plus(1, WEEKS));
    }

}
