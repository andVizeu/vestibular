package br.com.vestibular.core.domain;

import br.com.vestibular.core.usecase.candidato.CreateCandidatoUseCase;
import br.com.vestibular.core.usecase.candidato.UpdateCandidatoUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Candidato {

    private Long id;

    private Vestibular vestibular;

    private String nome;

    private LocalDateTime dataNascimento;

    private String cpf;

    private Curso curso;

    private Sala sala;

    private Candidato(String nome, LocalDateTime dataNascimento, String cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public static Candidato of(final CreateCandidatoUseCase.Request request) {
        return new Candidato(request.getNome(), request.getDataNascimento(), request.getCpf());
    }

    public void update(final UpdateCandidatoUseCase.Request request) {
        this.nome = request.getNome();
        this.dataNascimento = request.getDataNascimento();
        this.cpf = request.getCpf();
    }

}
