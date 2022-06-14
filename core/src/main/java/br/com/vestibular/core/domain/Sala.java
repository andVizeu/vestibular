package br.com.vestibular.core.domain;

import br.com.vestibular.core.usecase.sala.CreateSalaUseCase;
import br.com.vestibular.core.usecase.sala.UpdateSalaUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Sala {

    private Long id;

    private Vestibular vestibular;

    private String identificador;

    private String bloco;

    private Integer capacidade;

    private List<Candidato> candidatos;

    private Sala(String identificador, String bloco, Integer capacidade) {
        this.identificador = identificador;
        this.bloco = bloco;
        this.capacidade = capacidade;
    }

    public static Sala of(final CreateSalaUseCase.Request request) {
        return new Sala(request.getIdentificador(), request.getBloco(), request.getCapacidade());
    }

    public void update(final UpdateSalaUseCase.Request request) {
        this.identificador = request.getIdentificador();
        this.bloco = request.getBloco();
        this.capacidade = request.getCapacidade();
    }

}


