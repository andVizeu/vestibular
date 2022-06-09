package br.com.vestibular.api.entrypoint.rest.candidato.requests;

import java.time.LocalDateTime;

public class UpdateCandidatoDTO {
    private String nome;
    private LocalDateTime dataNascimento;
    private String cpf;
}
