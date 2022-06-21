package br.com.vestibular.api.entrypoint.rest.candidato.responses;

import br.com.vestibular.api.entrypoint.rest.curso.responses.CursoResponse;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalaResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoResponse {

    private Long id;

    private VestibularResponse vestibular;

    private String nome;

    private LocalDateTime dataNascimento;

    private String cpf;

    private CursoResponse curso;

    private SalaResponse sala;

}
