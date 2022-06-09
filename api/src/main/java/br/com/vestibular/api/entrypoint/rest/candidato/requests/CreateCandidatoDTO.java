package br.com.vestibular.api.entrypoint.rest.candidato.requests;

import java.time.LocalDateTime;

public class CreateCandidatoDTO {
    private String nome;
    private LocalDateTime dataNascimento;
    private String cpf;
}
