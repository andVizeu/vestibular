package br.com.vestibular.api.entrypoint.rest.sala;

import br.com.vestibular.api.entrypoint.rest.sala.requests.CreateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.requests.UpdateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalaResponse;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalasResponse;
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
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaController {

    @PostMapping
    ResponseEntity<SalaResponse> createSala(@RequestBody final CreateSalaDTO createSala) {
        return null;
    }

    @GetMapping
    ResponseEntity<SalasResponse> ListSalas() {
        return null;
    }

    @GetMapping("/salaId")
    ResponseEntity<SalaResponse> getSala(@PathVariable final Long salaId) {
        return null;
    }

    @PatchMapping("/salaId")
    ResponseEntity<SalaResponse> updateSala(@PathVariable final Long salaId,
                                                      @RequestBody final UpdateSalaDTO updateSala) {
        return null;
    }

    @DeleteMapping("/salaId")
    ResponseEntity<Void> deleteSala(@PathVariable final String salaId) {
        return null;
    }

}
