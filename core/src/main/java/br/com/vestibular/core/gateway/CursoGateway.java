package br.com.vestibular.core.gateway;

import br.com.vestibular.core.domain.Curso;

import java.util.List;
import java.util.UUID;

public interface CursoGateway {

    Curso addCurso(Curso curso, UUID VestibularUUID);

    List<Curso> listCursos(UUID VestibularUUID);

    Curso getCurso(UUID cursoUUID, UUID VestibularUUID);

    Curso updateCurso(Curso curso, UUID VestibularUUID);

    void deleteCurso(UUID cursoUUID, UUID VestibularUUID);

    boolean existsCurso(UUID cursoUUID);
}
