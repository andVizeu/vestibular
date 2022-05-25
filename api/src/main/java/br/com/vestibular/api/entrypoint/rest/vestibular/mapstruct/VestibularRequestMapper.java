package br.com.vestibular.api.entrypoint.rest.vestibular.mapstruct;

import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.UpdateVestibularDTO;
import br.com.vestibular.core.usecase.vestibular.CreateVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.UpdateVestibularUseCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VestibularRequestMapper {

    CreateVestibularUseCase.Request toRequest(final CreateVestibularDTO createVestibular);

    UpdateVestibularUseCase.Request toRequest(final UpdateVestibularDTO updateVestibularDTO, final String vestibularUUID);

}
