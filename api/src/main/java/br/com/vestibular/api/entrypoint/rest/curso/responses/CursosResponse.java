package br.com.vestibular.api.entrypoint.rest.curso.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CursosResponse {
    private List<CursoResponse> cursos;
}
