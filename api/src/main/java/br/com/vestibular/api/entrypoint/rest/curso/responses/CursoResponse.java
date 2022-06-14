package br.com.vestibular.api.entrypoint.rest.curso.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponse {

    private String nome;

    private UUID cursoUUID;

}
