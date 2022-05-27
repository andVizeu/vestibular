package br.com.vestibular.api.mapstruct;

import br.com.vestibular.api.entrypoint.rest.curso.requests.CreateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.curso.requests.UpdateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.UpdateVestibularDTO;
import br.com.vestibular.core.usecase.curso.CreateCursoUseCase;
import br.com.vestibular.core.usecase.curso.UpdateCursoUseCase;
import br.com.vestibular.core.usecase.vestibular.CreateVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.UpdateVestibularUseCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    CreateVestibularUseCase.Request toRequest(CreateVestibularDTO createVestibularDTO);

    UpdateVestibularUseCase.Request toRequest(UpdateVestibularDTO updateVestibularDTO, String vestibularUUID);

    CreateCursoUseCase.Request toRequest(CreateCursoDTO createCursoDTO);

    UpdateCursoUseCase.Request toRequest(String cursoUUID, UpdateCursoDTO updateCursoDTO);

}
