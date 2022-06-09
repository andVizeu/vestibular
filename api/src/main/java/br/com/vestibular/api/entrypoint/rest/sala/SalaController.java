package br.com.vestibular.api.entrypoint.rest.sala;


import br.com.vestibular.api.entrypoint.rest.sala.requests.CreateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.requests.UpdateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalaResponse;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalasResponse;
import br.com.vestibular.api.mapstruct.RequestMapper;
import br.com.vestibular.api.mapstruct.ResponseMapper;
import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.usecase.sala.CreateSalaUseCase;
import br.com.vestibular.core.usecase.sala.DeleteSalaUseCase;
import br.com.vestibular.core.usecase.sala.GetSalaUseCase;
import br.com.vestibular.core.usecase.sala.ListSalasUseCase;
import br.com.vestibular.core.usecase.sala.UpdateSalaUseCase;
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
public class SalaController {

    private final CreateSalaUseCase createSala;
    private final GetSalaUseCase getSala;
    private final DeleteSalaUseCase deleteSala;
    private final ListSalasUseCase listSalas;
    private final UpdateSalaUseCase updateSala;

    private final RequestMapper requestMapper = Mappers.getMapper(RequestMapper.class);
    private final ResponseMapper responseMapper = Mappers.getMapper(ResponseMapper.class);


    @PostMapping("/{vestibularUUID}/salas")
    ResponseEntity<SalaResponse> createSala(@PathVariable String vestibularUUID,
                                            @RequestBody final CreateSalaDTO createSalaDTO) {
        log.info("[SalaController] Recebendo create sala: {}", createSalaDTO);
        final CreateSalaUseCase.Request request = requestMapper.toRequest(createSalaDTO, vestibularUUID);
        final Sala sala = createSala.execute(request);
        log.info("[SalaoController] Retorno create sala: {}", sala);
        return ResponseEntity.ok(responseMapper.toResponse(sala));
    }

    @GetMapping("/{vestibularUUID}/salas")
    ResponseEntity<SalasResponse> listSalas(@PathVariable String vestibularUUID) {
        log.info("[SalaController] Recebendo list sala");
        final ListSalasUseCase.Request request = new ListSalasUseCase.Request(vestibularUUID);
        final List<Sala> salas = listSalas.execute(request);
        final List<SalaResponse> response = salas.stream().map(responseMapper::toResponse).collect(Collectors.toList());
        log.info("[SalaController] Retorno list salas: {}", response);
        return ResponseEntity.ok(new SalasResponse(response));
    }

    @GetMapping("/{vestibularUUID}/salas/{salaId}")
    ResponseEntity<SalaResponse> getSala(@PathVariable String vestibularUUID,
                                         @PathVariable String salaUUID) {
        log.info("[SalaController] Recebendo get sala: {}", salaUUID);
        final GetSalaUseCase.Request request = new GetSalaUseCase.Request(vestibularUUID, salaUUID);
        final Sala sala = getSala.execute(request);
        log.info("[SalaController] Retorno get sala: {}", sala);
        return ResponseEntity.ok(responseMapper.toResponse(sala));
    }

    @PatchMapping("/{vestibularUUID}/salas/{salaId}")
    ResponseEntity<SalaResponse> updateSala(@PathVariable final String vestibularUUID,
                                              @PathVariable final String salaUUID,
                                              @RequestBody final UpdateSalaDTO updateSalaDTO) {
        log.info("[VestibularController] Recebendo Update sala --> salaUUID: {}, DTO: {}", salaUUID, updateSalaDTO);
        final UpdateSalaUseCase.Request request = requestMapper.toRequest(updateSalaDTO, vestibularUUID, salaUUID);
        final Sala sala = updateSala.execute(request);
        log.info("[SalaController] Retorno update sala: {}", sala);
        return ResponseEntity.ok(responseMapper.toResponse(sala));
    }

    @DeleteMapping("/{vestibularUUID}/salas/{salaId}")
    ResponseEntity<Void> deleteSala(@PathVariable String vestibularUUID,
                                    @PathVariable  String salaUUID) {
        log.info("[SalaController] Recebendo delete sala: {}", salaUUID);
        final DeleteSalaUseCase.Request request = new DeleteSalaUseCase.Request(vestibularUUID, salaUUID);
        deleteSala.execute(request);
        log.info("[SalaController] Sala deletado");
        return ResponseEntity.ok().build();
    }

}
