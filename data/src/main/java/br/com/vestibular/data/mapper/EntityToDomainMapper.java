package br.com.vestibular.data.mapper;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.data.entity.CandidatoEntity;
import br.com.vestibular.data.entity.CursoEntity;
import br.com.vestibular.data.entity.VestibularEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToDomainMapper {

    Vestibular toDomain(VestibularEntity entity);

    Curso toDomain(CursoEntity entity);

    Candidato toDomain(CandidatoEntity entity);
}
