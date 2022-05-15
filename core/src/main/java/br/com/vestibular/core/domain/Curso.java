package br.com.vestibular.core.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Curso {

    private String nome;

    private UUID cursoUUID;

    private List<Vestibular> vestibulares;

    private List<Candidato> candidatos;

}
