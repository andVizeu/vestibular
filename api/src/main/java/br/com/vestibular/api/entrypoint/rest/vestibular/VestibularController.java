package br.com.vestibular.api.entrypoint.rest.vestibular;

import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.UpdateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularesResponse;
import br.com.vestibular.core.usecase.vestibular.CreateVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.GetVestibularUseCase;
import br.com.vestibular.core.usecase.vestibular.ListVestibularUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vestibulares")
@RequiredArgsConstructor
public class VestibularController {

    private final CreateVestibularUseCase createVestibular;
    private final ListVestibularUseCase listVestibular;
    private final GetVestibularUseCase getVestibular;


    @PostMapping
    ResponseEntity<VestibularResponse> createVestibular(@RequestBody final CreateVestibularDTO createVestibular) {
        return null;
    }

    @GetMapping
    ResponseEntity<VestibularesResponse> listVestibular() {
        return null;
    }

    @GetMapping("/vestibularUUID")
    ResponseEntity<VestibularResponse> getVestibular(@PathVariable final String vestibularUUID) {
        return null;
    }

    @PatchMapping("/vestibularUUID")
    ResponseEntity<VestibularResponse> updateVestibular(@PathVariable final String vestibularUUID,
                                                        @RequestBody final UpdateVestibularDTO updateVestibular) {
        return null;
    }

    @DeleteMapping("/vestibularUUID")
    ResponseEntity<Void> deleteVestibular(@PathVariable final String vestibularUUID) {
        return null;
    }

}