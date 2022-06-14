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
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityToDomainMapper {

    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "salas", ignore = true)
    @Mapping(target = "candidatos", ignore = true)
    Vestibular toDomain(VestibularEntity entity);

    @Mapping(target = "vestibulares", ignore = true)
    @Mapping(target = "candidatos", ignore = true)
    Curso toDomain(CursoEntity entity);

    Candidato toDomain(CandidatoEntity entity);

    Sala toDomain(SalaEntity entity);
}
