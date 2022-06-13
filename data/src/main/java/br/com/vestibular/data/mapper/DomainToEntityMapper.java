package br.com.vestibular.data.mapper;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.data.entity.CandidatoEntity;
import br.com.vestibular.data.entity.CursoEntity;
import br.com.vestibular.data.entity.SalaEntity;
import br.com.vestibular.data.entity.VestibularEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomainToEntityMapper {

    VestibularEntity toEntity(Vestibular vestibular);

    CursoEntity toEntity(Curso curso);

    CandidatoEntity toEntity(Candidato candidato);

    SalaEntity toEntity(Sala sala);
}
