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
@RequestMapping("/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    @PostMapping
    ResponseEntity<CandidatoResponse> createCandidato(@RequestBody final CreateCandidatoDTO createCandidato) {
        return null;
    }

    @GetMapping
    ResponseEntity<CandidatosResponse> ListCandidatos() {
        return null;
    }

    @GetMapping("/candidatoId")
    ResponseEntity<CandidatoResponse> getCandidato(@PathVariable final Long candidatoId) {
        return null;
    }

    @PatchMapping("/candidatoId")
    ResponseEntity<CandidatoResponse> updateCandidato(@PathVariable final Long candidatoId,
                                                      @RequestBody final UpdateCandidatoDTO updateCandidato) {
        return null;
    }

    @DeleteMapping("/candidatoId")
    ResponseEntity<Void> deleteCandidato(@PathVariable final String candidatoId) {
        return null;
    }

}
