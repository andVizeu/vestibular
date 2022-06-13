package br.com.vestibular.integrationtests.config;

import br.com.vestibular.core.domain.Vestibular;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

public class CreateEntityUtils {

    public static Vestibular createVestibular() {
        return new Vestibular(null, LocalDateTime.now(), LocalDateTime.now().plus(1, HOURS), null, null, null);
    }

}
