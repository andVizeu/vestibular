package br.com.vestibular.api.entrypoint.rest.candidato.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCandidatoDTO {
    private String nome;
    private LocalDateTime dataNascimento;
    private String cpf;
}
