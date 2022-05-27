package br.com.vestibular.api.entrypoint.rest.vestibular;

import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.UpdateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularesResponse;
import br.com.vestibular.api.mapstruct.RequestMapper;
import br.com.vestibular.api.mapstruct.ResponseMapper;
import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.usecase.vestibular.CreateVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.DeleteVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.GetVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.ListVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.UpdateVestibularUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/vestibulares")
@RequiredArgsConstructor
public class VestibularController {

    private final CreateVestibularUseCase createVestibular;
    private final ListVestibularUseCase listVestibular;
    private final GetVestibularUseCase getVestibular;
    private final UpdateVestibularUseCase updateVestibular;
    private final DeleteVestibularUseCase deleteVestibular;
    private final RequestMapper requestMapper = Mappers.getMapper(RequestMapper.class);
    private final ResponseMapper responseMapper = Mappers.getMapper(ResponseMapper.class);


    @PostMapping
    ResponseEntity<VestibularResponse> createVestibular(@RequestBody final CreateVestibularDTO createVestibularDTO) {
        log.info("[VestibularController] Recebendo Create vestibular: {}", createVestibularDTO);
        final CreateVestibularUseCase.Request request =  requestMapper.toRequest(createVestibularDTO);
        final Vestibular vestibular = createVestibular.execute(request);
        log.info("[VestibularController] Retorno create vestibular: {}", vestibular);
        return ResponseEntity.ok(responseMapper.toResponse(vestibular));
    }

    @GetMapping
    ResponseEntity<VestibularesResponse> listVestibular() {
        log.info("[VestibularController] List vestibular");
        final List<Vestibular> vestibulares = listVestibular.execute();
        final List<VestibularResponse> response = vestibulares.stream().map(responseMapper::toResponse).collect(Collectors.toList());
        log.info("[VestibularController] Retorno List vestibular: {}", response);
        return ResponseEntity.ok(new VestibularesResponse(response));
    }

    @GetMapping("/{vestibularUUID}")
    ResponseEntity<VestibularResponse> getVestibular(@PathVariable final String vestibularUUID) {
        log.info("[VestibularController] Recebendo Get vestibular: {}", vestibularUUID);
        final GetVestibularUseCase.Request request = new GetVestibularUseCase.Request(vestibularUUID);
        final Vestibular vestibular = getVestibular.execute(request);
        log.info("[VestibularController] Retorno Get vestibular: {}", vestibular);
        return ResponseEntity.ok(responseMapper.toResponse(vestibular));
    }

    @PatchMapping("/{vestibularUUID}")
    ResponseEntity<VestibularResponse> updateVestibular(@PathVariable final String vestibularUUID,
                                                        @RequestBody final UpdateVestibularDTO updateVestibularDTO) {
        log.info("[VestibularController] Recebendo Update vestibular --> vestibularUUID: {}, DTO: {}", vestibularUUID, updateVestibularDTO);
        final UpdateVestibularUseCase.Request request = requestMapper.toRequest(updateVestibularDTO, vestibularUUID);
        final Vestibular vestibular = updateVestibular.execute(request);
        log.info("[VestibularController] Retorno Update vestibular: {}", vestibular);
        return ResponseEntity.ok(responseMapper.toResponse(vestibular));
    }

    @DeleteMapping("/{vestibularUUID}")
    ResponseEntity<Void> deleteVestibular(@PathVariable final String vestibularUUID) {
        log.info("[VestibularController] Recebendo Delete vestibular: {}", vestibularUUID);
        final DeleteVestibularUseCase.Request request = new DeleteVestibularUseCase.Request(vestibularUUID);
        deleteVestibular.execute(request);
        log.info("[VestibularController] Vestibular deletado");
        return ResponseEntity.ok().build();
    }

}
