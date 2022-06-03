package br.com.vestibular.data.gateway;

import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.gateway.CursoGateway;
import br.com.vestibular.data.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CursoGatewayImpl implements CursoGateway {

    private CursoRepository repository;

    @Override
    public Curso addCurso(Curso curso, UUID VestibularUUID) {
        return null;
    }

    @Override
    public List<Curso> listCursos(UUID VestibularUUID) {
        return null;
    }

    @Override
    public Curso getCurso(UUID cursoUUID, UUID VestibularUUID) {
        return null;
    }

    @Override
    public Curso updateCurso(Curso curso, UUID VestibularUUID) {
        return null;
    }

    @Override
    public void deleteCurso(UUID cursoUUID, UUID VestibularUUID) {

    }

    @Override
    public boolean existsCurso(UUID cursoUUID) {
        return false;
    }
}
