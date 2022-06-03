package br.com.vestibular.api.entrypoint.rest.sala;

import br.com.vestibular.api.entrypoint.rest.sala.requests.CreateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.requests.UpdateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalaResponse;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalasResponse;
import br.com.vestibular.core.usecase.sala.CreateSalaUseCase;
import br.com.vestibular.core.usecase.sala.DeleteSalaUseCase;
import br.com.vestibular.core.usecase.sala.GetSalaUseCase;
import br.com.vestibular.core.usecase.sala.ListSalasUseCase;
import br.com.vestibular.core.usecase.sala.UpdateSalaUseCase;
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
public class SalaController {

    private final CreateSalaUseCase createSala;
    private final GetSalaUseCase getSala;
    private final DeleteSalaUseCase deleteSala;
    private final ListSalasUseCase listSalas;
    private final UpdateSalaUseCase updateSala;


    @PostMapping("/{vestibularUUID}/salas")
    ResponseEntity<SalaResponse> createSala(@PathVariable String vestibularUUID,
                                            @RequestBody final CreateSalaDTO createSala) {
        return null;
    }

    @GetMapping("/{vestibularUUID}/salas")
    ResponseEntity<SalasResponse> listSalas(@PathVariable String vestibularUUID) {
        return null;
    }

    @GetMapping("/{vestibularUUID}/salas/{salaId}")
    ResponseEntity<SalaResponse> getSala(@PathVariable String vestibularUUID,
                                         @PathVariable final Long salaId) {
        return null;
    }

    @PatchMapping("/{vestibularUUID}/salas/{salaId}")
    ResponseEntity<SalaResponse> updateSala(@PathVariable String vestibularUUID,
                                            @PathVariable final Long salaId,
                                            @RequestBody final UpdateSalaDTO updateSala) {
        return null;
    }

    @DeleteMapping("/{vestibularUUID}/salas/{salaId}")
    ResponseEntity<Void> deleteSala(@PathVariable String vestibularUUID,
                                    @PathVariable final String salaId) {
        return null;
    }

}
