package br.com.vestibular.api.mapstruct;

import br.com.vestibular.api.entrypoint.rest.candidato.requests.CreateCandidatoDTO;
import br.com.vestibular.api.entrypoint.rest.candidato.requests.UpdateCandidatoDTO;
import br.com.vestibular.api.entrypoint.rest.curso.requests.CreateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.curso.requests.UpdateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.sala.requests.CreateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.requests.UpdateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.UpdateVestibularDTO;
import br.com.vestibular.core.usecase.candidato.CreateCandidatoUseCase;
import br.com.vestibular.core.usecase.candidato.UpdateCandidatoUseCase;
import br.com.vestibular.core.usecase.curso.CreateCursoUseCase;
import br.com.vestibular.core.usecase.curso.UpdateCursoUseCase;
import br.com.vestibular.core.usecase.sala.CreateSalaUseCase;
import br.com.vestibular.core.usecase.sala.UpdateSalaUseCase;
import br.com.vestibular.core.usecase.vestibular.CreateVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.UpdateVestibularUseCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    CreateVestibularUseCase.Request toRequest(CreateVestibularDTO createVestibularDTO);

    UpdateVestibularUseCase.Request toRequest(UpdateVestibularDTO updateVestibularDTO, String vestibularUUID);

    CreateCursoUseCase.Request toRequest(CreateCursoDTO createCursoDTO, String vestibularUUID);

    UpdateCursoUseCase.Request toRequest(UpdateCursoDTO updateCursoDTO, String vestibularUUID, String cursoUUID);

    CreateSalaUseCase.Request toRequest(CreateSalaDTO createSalaDTO, String vestibularUUID);

    UpdateSalaUseCase.Request toRequest(UpdateSalaDTO updateSalaDTO, String vestibularUUID, Long salaId);

    CreateCandidatoUseCase.Request toRequest(CreateCandidatoDTO createCandidatoDTO, String vestibularUUID, String cursoUUID);

    UpdateCandidatoUseCase.Request toRequest(String vestibularUUID, String cursoUUID, Long candidatoId, UpdateCandidatoDTO updateCandidatoDTO);
}
