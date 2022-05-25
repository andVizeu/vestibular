package br.com.vestibular.api.entrypoint.rest.vestibular.responses;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Sala;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class VestibularResponse {

    private UUID vestibularUUID;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private List<Curso> cursos;

    private List<Sala> salas;

    private List<Candidato> candidatos;

}
