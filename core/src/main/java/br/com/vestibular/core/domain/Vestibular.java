package br.com.vestibular.core.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Vestibular {

    private UUID vestibularUUID;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private List<Curso> cursos = new ArrayList<>();

    private List<Sala> salas;

    private List<Candidato> candidatos;
}
