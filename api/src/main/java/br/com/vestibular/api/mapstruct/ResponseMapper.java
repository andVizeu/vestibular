package br.com.vestibular.api.mapstruct;

import br.com.vestibular.api.entrypoint.rest.curso.responses.CursoResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Vestibular;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    VestibularResponse toResponse(Vestibular vestibular);

    CursoResponse toResponse(Curso curso);
}
