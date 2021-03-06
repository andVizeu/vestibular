package br.com.vestibular.core.domain;

import br.com.vestibular.core.usecase.curso.UpdateCursoUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Curso {

    private String nome;

    private UUID cursoUUID;

    private List<Vestibular> vestibulares;

    private List<Candidato> candidatos;


    private Curso(String nome) {
        this.nome = nome;
    }

    public static Curso of(final String nome) {
        return new Curso(nome);
    }

    public void update(final UpdateCursoUseCase.Request request) {
        this.nome = request.getNome();
    }

}
