package br.com.vestibular.core.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Sala {

    private Vestibular vestibular;

    private UUID cursoUUID;

    private String identificador;

    private String bloco;

    private Integer capacidade;

    private List<Candidato> candidatos;

}
