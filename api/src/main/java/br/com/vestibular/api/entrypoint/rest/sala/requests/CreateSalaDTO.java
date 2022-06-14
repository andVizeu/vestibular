package br.com.vestibular.api.entrypoint.rest.sala.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSalaDTO {

    private String identificador;

    private String bloco;

    private Integer capacidade;

}
