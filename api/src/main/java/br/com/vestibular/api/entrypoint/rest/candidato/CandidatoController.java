package br.com.vestibular.api.entrypoint.rest.candidato;

import br.com.vestibular.api.entrypoint.rest.candidato.requests.CreateCandidatoDTO;
import br.com.vestibular.api.entrypoint.rest.candidato.requests.UpdateCandidatoDTO;
import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatoResponse;
import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatosResponse;
import br.com.vestibular.api.mapstruct.RequestMapper;
import br.com.vestibular.api.mapstruct.ResponseMapper;
import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.usecase.candidato.AtribuirCandidatosUseCase;
import br.com.vestibular.core.usecase.candidato.CreateCandidatoUseCase;
import br.com.vestibular.core.usecase.candidato.DeleteCandidatoUseCase;
import br.com.vestibular.core.usecase.candidato.GetCandidatoUseCase;
import br.com.vestibular.core.usecase.candidato.ListCandidatosUseCase;
import br.com.vestibular.core.usecase.candidato.UpdateCandidatoUseCase;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/vestibulares")
@RequiredArgsConstructor
public class CandidatoController {

    private final CreateCandidatoUseCase createCandidato;
    private final AtribuirCandidatosUseCase atribuirCandidatos;
    private final ListCandidatosUseCase listCandidatos;
    private final GetCandidatoUseCase getCandidato;
    private final UpdateCandidatoUseCase updateCandidato;
    private final DeleteCandidatoUseCase deleteCandidato;
    private final RequestMapper requestMapper = Mappers.getMapper(RequestMapper.class);
    private final ResponseMapper responseMapper = Mappers.getMapper(ResponseMapper.class);

    @PostMapping("/{vestibularUUID}/cursos/{cursoUUID}/candidatos")
    ResponseEntity<CandidatosResponse> createCandidato(@PathVariable final String vestibularUUID,
                                                      @PathVariable final String cursoUUID,
                                                      @RequestBody final CreateCandidatoDTO createCandidatoDTO) {
        log.info("[CandidatoController] Recebendo create candidato : {}", createCandidatoDTO);
        final CreateCandidatoUseCase.Request request = requestMapper.toRequest(createCandidatoDTO, vestibularUUID, cursoUUID);
        final List<Candidato> candidatos = createCandidato.execute(request);
        final List<CandidatoResponse> response = candidatos.stream().map(responseMapper::toResponse).collect(Collectors.toList());
        log.info("[CandidatoController] Retorno create candidato: {}", response.size());
        return ResponseEntity.ok(new CandidatosResponse(response));
    }

    @GetMapping("/{vestibularUUID}/assing")
    ResponseEntity<CandidatosResponse> atribuirCadidatoSala(@PathVariable final String vestibularUUID) {
        log.info("[CandidatoController] Atribuindo candidatos");
        final AtribuirCandidatosUseCase.Request request = new AtribuirCandidatosUseCase.Request(vestibularUUID);
        final List<Candidato> candidatos = atribuirCandidatos.execute(request);
        final List<CandidatoResponse> response = candidatos.stream().map(responseMapper::toResponse).collect(Collectors.toList());
        log.info("[CandidatoController] Retorno atibuir candidato: {}", response.size());
        return ResponseEntity.ok(new CandidatosResponse(response));
    }

    @GetMapping("/{vestibularUUID}/candidatos")
    ResponseEntity<CandidatosResponse> listCandidatos(@PathVariable final String vestibularUUID,
                                                      @RequestParam(required = false) String cursoUUID) {
        log.info("[CandidatoController] Recebendo list candidatos");
        final ListCandidatosUseCase.Request request = new ListCandidatosUseCase.Request(vestibularUUID, cursoUUID);
        final List<Candidato> candidatos = listCandidatos.execute(request);
        final List<CandidatoResponse> response = candidatos.stream().map(responseMapper::toResponse).collect(Collectors.toList());
        log.info("[CandidatoController] Retorno list candidatos: {}", response);
        return ResponseEntity.ok(new CandidatosResponse(response));
    }

    @GetMapping("/{vestibularUUID}/candidatos/{candidatoId}")
    ResponseEntity<CandidatoResponse> getCandidato(@PathVariable final String vestibularUUID,
                                                   @PathVariable final Long candidatoId) {
        log.info("[CandidatoController] Recebendo get candidato: {}", candidatoId);
        final GetCandidatoUseCase.Request request = new GetCandidatoUseCase.Request(vestibularUUID, candidatoId);
        final Candidato candidato = getCandidato.execute(request);
        log.info("[CandidatoController] Retorno get candidato: {}", candidato);
        return ResponseEntity.ok(responseMapper.toResponse(candidato));
    }

    @PatchMapping("/{vestibularUUID}/cursos/{cursoUUID}/candidatos/{candidatoId}")
    ResponseEntity<CandidatoResponse> updateCandidato(@PathVariable final String vestibularUUID,
                                                      @PathVariable final String cursoUUID,
                                                      @PathVariable final Long candidatoId,
                                                      @RequestBody final UpdateCandidatoDTO updateCandidatoDTO) {
        log.info("[CandidatoController] Recebendo Update candidato --> candidatoId: {}, dto: {}", candidatoId, updateCandidatoDTO);
        final UpdateCandidatoUseCase.Request request = requestMapper.toRequest(vestibularUUID, cursoUUID, candidatoId, updateCandidatoDTO);
        final Candidato candidato = updateCandidato.execute(request);
        log.info("[CandidatoController] Retorno update candidato: {}", candidato);
        return ResponseEntity.ok(responseMapper.toResponse(candidato));
    }

    @DeleteMapping("/{vestibularUUID}/cursos/{cursoUUID}/candidatos/{candidatoId}")
    ResponseEntity<Void> deleteCandidato(@PathVariable final String vestibularUUID,
                                         @PathVariable final String cursoUUID,
                                         @PathVariable final Long candidatoId) {
        log.info("[CandidatoController] Recebendo delete candidato: {}", candidatoId);
        final DeleteCandidatoUseCase.Request request = new DeleteCandidatoUseCase.Request(vestibularUUID, cursoUUID, candidatoId);
        deleteCandidato.execute(request);
        log.info("[CandidatoController] Candidato deletado");
        return ResponseEntity.ok().build();
    }

}
