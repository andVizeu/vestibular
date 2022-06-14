package br.com.vestibular.core.gateway;

import br.com.vestibular.core.domain.Curso;

import java.util.List;
import java.util.UUID;

public interface CursoGateway {

    List<Curso> addCurso(Curso curso, UUID vestibularUUID);

    List<Curso> listCursos(UUID vestibularUUID);

    Curso getCurso(UUID cursoUUID);

    Curso updateCurso(Curso curso);

    List<Curso> deleteCurso(UUID vestibularUUID, UUID cursoUUID);

    boolean existsCurso(UUID cursoUUID);
}
