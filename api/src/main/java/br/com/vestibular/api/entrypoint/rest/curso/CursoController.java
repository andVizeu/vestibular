package br.com.vestibular.api.entrypoint.rest.curso;

import br.com.vestibular.api.entrypoint.rest.curso.requests.CreateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.curso.requests.UpdateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.curso.responses.CursoResponse;
import br.com.vestibular.api.entrypoint.rest.curso.responses.CursosResponse;
import br.com.vestibular.api.mapstruct.RequestMapper;
import br.com.vestibular.api.mapstruct.ResponseMapper;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.usecase.curso.CreateCursoUseCase;
import br.com.vestibular.core.usecase.curso.DeleteCursoUseCase;
import br.com.vestibular.core.usecase.curso.GetCursoUseCase;
import br.com.vestibular.core.usecase.curso.ListCursoUseCase;
import br.com.vestibular.core.usecase.curso.UpdateCursoUseCase;
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
public class CursoController {

    private final CreateCursoUseCase createCurso;
    private final GetCursoUseCase getCurso;
    private final ListCursoUseCase listCurso;
    private final DeleteCursoUseCase deleteCurso;
    private final UpdateCursoUseCase updateCurso;
    private final RequestMapper requestMapper = Mappers.getMapper(RequestMapper.class);
    private final ResponseMapper responseMapper = Mappers.getMapper(ResponseMapper.class);


    @PostMapping("/{vestibularUUID}/cursos")
    ResponseEntity<CursoResponse> createCurso(@PathVariable String vestibularUUID,
                                              @RequestBody final CreateCursoDTO createCursoDTO) {
        log.info("[CursoController] Recebendo create curso: {}", createCursoDTO);
        final CreateCursoUseCase.Request request = requestMapper.toRequest(createCursoDTO, vestibularUUID);
        final Curso curso = createCurso.execute(request);
        log.info("[CursoController] Retorno create curso: {}", curso);
        return ResponseEntity.ok(responseMapper.toResponse(curso));
    }

    @GetMapping("/{vestibularUUID}/cursos")
    ResponseEntity<CursosResponse> listCurso(@PathVariable String vestibularUUID) {
        log.info("[CursoController] Recebendo list curso");
        final List<Curso> cursos = listCurso.execute();
        final List<CursoResponse> response = cursos.stream().map(responseMapper::toResponse).collect(Collectors.toList());
        log.info("[CursoController] Retorno list cursos: {}", response);
        return ResponseEntity.ok(new CursosResponse(response));
    }

    @GetMapping("/{vestibularUUID}/cursos/{cursoUUID}")
    ResponseEntity<CursoResponse> getCurso(@PathVariable String vestibularUUID, @PathVariable final String cursoUUID) {
        log.info("[CursoController] Recebendo get curso: {}", cursoUUID);
        final GetCursoUseCase.Request request = new GetCursoUseCase.Request(vestibularUUID, cursoUUID);
        final Curso curso = getCurso.execute(request);
        log.info("[CursoController] Retorno get curso: {}", curso);
        return ResponseEntity.ok(responseMapper.toResponse(curso));
    }

    @PatchMapping("/{vestibularUUID}/cursos/{cursoUUID}")
    ResponseEntity<CursoResponse> updateCurso(@PathVariable String vestibularUUID,
                                              @PathVariable final String cursoUUID,
                                              @RequestBody final UpdateCursoDTO updateCursoDTO) {
        log.info("[VestibularController] Recebendo Update curso --> cursoUUID: {}, DTO: {}", cursoUUID, updateCursoDTO);
        final UpdateCursoUseCase.Request request = requestMapper.toRequest(updateCursoDTO, cursoUUID);
        final Curso curso = updateCurso.execute(request);
        log.info("[CursoController] Retorno update curso: {}", curso);
        return ResponseEntity.ok(responseMapper.toResponse(curso));
    }

    @DeleteMapping("/{vestibularUUID}/cursos/{cursoUUID}")
    ResponseEntity<Void> deleteCurso(@PathVariable String vestibularUUID,
                                     @PathVariable final String cursoUUID) {
        log.info("[CursoController] Recebendo delete curso: {}", cursoUUID);
        final DeleteCursoUseCase.Request request = new DeleteCursoUseCase.Request(cursoUUID);
        deleteCurso.execute(request);
        log.info("[CursoController] Curso deletado");
        return ResponseEntity.ok().build();
    }

}
