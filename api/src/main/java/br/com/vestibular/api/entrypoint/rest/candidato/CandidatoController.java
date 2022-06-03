package br.com.vestibular.api.entrypoint.rest.candidato;

import br.com.vestibular.api.entrypoint.rest.candidato.requests.CreateCandidatoDTO;
import br.com.vestibular.api.entrypoint.rest.candidato.requests.UpdateCandidatoDTO;
import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatoResponse;
import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatosResponse;
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
public class CandidatoController {

    @PostMapping("/{vestibularUUID}/candidatos")
    ResponseEntity<CandidatoResponse> createCandidato(@PathVariable String vestibularUUID,
                                                      @RequestBody final CreateCandidatoDTO createCandidato) {
        return null;
    }

    @GetMapping("/{vestibularUUID}/candidatos")
    ResponseEntity<CandidatosResponse> listCandidatos(@PathVariable String vestibularUUID) {
        return null;
    }

    @GetMapping("/{vestibularUUID}/candidatos/{candidatoId}")
    ResponseEntity<CandidatoResponse> getCandidato(@PathVariable String vestibularUUID,
                                                   @PathVariable final Long candidatoId) {
        return null;
    }

    @PatchMapping("/{vestibularUUID}/candidatos/{candidatoId}")
    ResponseEntity<CandidatoResponse> updateCandidato(@PathVariable String vestibularUUID,
                                                      @PathVariable final Long candidatoId,
                                                      @RequestBody final UpdateCandidatoDTO updateCandidato) {
        return null;
    }

    @DeleteMapping("/{vestibularUUID}/candidatos/{candidatoId}")
    ResponseEntity<Void> deleteCandidato(@PathVariable String vestibularUUID,
                                         @PathVariable final String candidatoId) {
        return null;
    }

}
