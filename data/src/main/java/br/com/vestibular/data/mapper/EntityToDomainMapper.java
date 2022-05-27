package br.com.vestibular.data.mapper;

import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.data.entity.VestibularEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToDomainMapper {

    Vestibular toDomain(VestibularEntity savedEntity);

}
