package br.com.vestibular.api.entrypoint.rest.curso.responses;

import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatoResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponse {

    private String nome;

    private UUID cursoUUID;

    private List<VestibularResponse> vestibulares;

    private List<CandidatoResponse> candidatos;

}
