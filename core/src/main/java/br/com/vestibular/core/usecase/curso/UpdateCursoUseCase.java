package br.com.vestibular.core.usecase.curso;

import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.exceptions.CursoNotFoundExeption;
import br.com.vestibular.core.exceptions.VestibularNotFoundException;
import br.com.vestibular.core.gateway.CursoGateway;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.core.utils.UuidConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateCursoUseCase {

    private final VestibularGateway vestibularGateway;
    private final CursoGateway cursoGateway;

    public Curso execute(final Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        final UUID cursoUUID = UuidConverterHelper.convertToUUId(request.cursoUUID);
        final Curso curso = cursoGateway.getCurso(cursoUUID);
        if (isNull(curso)) {
            throw new CursoNotFoundExeption(request.cursoUUID);
        }

        curso.update(request);
        return cursoGateway.updateCurso(curso);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String nome;
        private String vestibularUUID;
        private String cursoUUID;
    }

}
