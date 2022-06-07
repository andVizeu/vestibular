package br.com.vestibular.core.domain;

import br.com.vestibular.core.usecase.curso.UpdateCursoUseCase;
import br.com.vestibular.core.usecase.sala.UpdateSalaUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Sala {

    private Vestibular vestibular;

    private UUID salaUUID;

    private String identificador;

    private String bloco;

    private Integer capacidade;

    private List<Candidato> candidatos;

    private Sala(String identificador) {
        this.identificador = identificador;
    }

    public static Sala of(final String identificador) {
        return new Sala(identificador);
    }

    public void update(final UpdateSalaUseCase.Request request) {
        this.identificador = request.getIdentificador();
    }

}


