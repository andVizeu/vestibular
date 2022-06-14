package br.com.vestibular.integrationtests;

import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.data.entity.VestibularEntity;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

public class CreateEntityUtils {

    public static Vestibular createVestibular() {
        return new Vestibular(null, LocalDateTime.now(), LocalDateTime.now().plus(1, HOURS),
                null, null, null);
    }

    public static VestibularEntity createVestibularEntity() {
        return new VestibularEntity(null, LocalDateTime.now(), LocalDateTime.now().plus(1, HOURS),
                null, null, null);
    }

    public static Curso createCurso() {
        return new Curso("curso", null, null, null);
    }

    public static Sala createSala(final Vestibular vestibular) {
        return new Sala(null, vestibular, "A101", "A", 30, null);
    }

}
