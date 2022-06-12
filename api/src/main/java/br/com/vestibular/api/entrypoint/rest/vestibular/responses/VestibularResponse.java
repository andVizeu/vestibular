package br.com.vestibular.api.entrypoint.rest.vestibular.responses;

import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatoResponse;
import br.com.vestibular.api.entrypoint.rest.curso.responses.CursoResponse;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalaResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VestibularResponse {

    private UUID vestibularUUID;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private List<CursoResponse> cursos;

    private List<SalaResponse> salas;

    private List<CandidatoResponse> candidatos;

}
