package br.com.vestibular.api.entrypoint.rest.vestibular.mapstruct;

import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import br.com.vestibular.core.domain.Vestibular;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VestibularResponseMapper {
    VestibularResponse toResponse(final Vestibular vestibular);
}
