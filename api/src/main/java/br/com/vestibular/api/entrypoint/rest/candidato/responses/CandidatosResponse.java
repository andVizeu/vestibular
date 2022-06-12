package br.com.vestibular.api.entrypoint.rest.candidato.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatosResponse {

    private List<CandidatoResponse> candidatos;

}
