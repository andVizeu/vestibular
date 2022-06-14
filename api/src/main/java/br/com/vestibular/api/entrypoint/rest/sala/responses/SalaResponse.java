package br.com.vestibular.api.entrypoint.rest.sala.responses;

import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatoResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaResponse {

    private VestibularResponse vestibular;

    private Long id;

    private String identificador;

    private String bloco;

    private Integer capacidade;

    private List<CandidatoResponse> candidatos;

}
