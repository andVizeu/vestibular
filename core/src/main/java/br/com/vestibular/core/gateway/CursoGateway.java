package br.com.vestibular.core.gateway;

import br.com.vestibular.core.domain.Curso;

import java.util.List;
import java.util.UUID;

public interface CursoGateway {

    Curso addCurso(Curso curso, UUID vestibularUUID);

    List<Curso> listCursos(UUID vestibularUUID);

    Curso getCurso(UUID cursoUUID);

    Curso updateCurso(Curso curso);

    void deleteCurso(UUID cursoUUID);

    boolean existsCurso(UUID cursoUUID);
}
