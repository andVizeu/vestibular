package br.com.vestibular.entrypoint.rest.curso;

import br.com.vestibular.entrypoint.rest.curso.requests.CreateCursoDTO;
import br.com.vestibular.entrypoint.rest.curso.requests.UpdateCursoDTO;
import br.com.vestibular.entrypoint.rest.curso.responses.CursoResponse;
import br.com.vestibular.entrypoint.rest.curso.responses.CursosResponse;
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
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    @PostMapping
    ResponseEntity<CursoResponse> createCurso(@RequestBody final CreateCursoDTO createCurso) {
        return null;
    }

    @GetMapping
    ResponseEntity<CursosResponse> ListCurso() {
        return null;
    }

    @GetMapping("/cursoUUID")
    ResponseEntity<CursoResponse> getCurso(@PathVariable final String cursoUUID) {
        return null;
    }

    @PatchMapping("/cursoUUID")
    ResponseEntity<CursoResponse> updateCurso(@PathVariable final String cursoUUID,
                                              @RequestBody final UpdateCursoDTO updateCurso) {
        return null;
    }

    @DeleteMapping("/cursoUUID")
    ResponseEntity<Void> deleteCurso(@PathVariable final String cursoUUID) {
        return null;
    }

}
