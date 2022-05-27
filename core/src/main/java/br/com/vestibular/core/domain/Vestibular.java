package br.com.vestibular.core.domain;

import br.com.vestibular.core.usecase.vestibular.UpdateVestibularUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Vestibular {

    private UUID vestibularUUID;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private List<Curso> cursos;

    private List<Sala> salas;

    private List<Candidato> candidatos;

    private Vestibular(final LocalDateTime dataInicio, final LocalDateTime dataFim) {
        super();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public static Vestibular of(final LocalDateTime dataInicio, final LocalDateTime dataFim) {
        return new Vestibular(dataInicio, dataFim);
    }

    public void update(UpdateVestibularUseCase.Request request) {
        this.dataInicio = request.getDataInicio();
        this.dataFim = request.getDataFim();
    }
}
