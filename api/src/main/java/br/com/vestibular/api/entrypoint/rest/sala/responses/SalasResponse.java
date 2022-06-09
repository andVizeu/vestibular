package br.com.vestibular.api.entrypoint.rest.sala.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SalasResponse {
    private List<SalaResponse> salas;
}
