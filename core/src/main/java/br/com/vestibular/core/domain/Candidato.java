package br.com.vestibular.core.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Candidato {

    private Vestibular vestibular;

    private String nome;

    private LocalDateTime dataNascimento;

    private String cpf;

    private Curso curso;

    private Sala sala;

}
