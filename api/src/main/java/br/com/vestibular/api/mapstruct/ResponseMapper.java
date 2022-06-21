package br.com.vestibular.api.mapstruct;

import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatoResponse;
import br.com.vestibular.api.entrypoint.rest.curso.responses.CursoResponse;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalaResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.domain.Vestibular;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    VestibularResponse toResponse(Vestibular vestibular);

    CandidatoResponse toResponse(Candidato candidato);

    CursoResponse toResponse(Curso curso);

    SalaResponse toResponse(Sala sala);

}
