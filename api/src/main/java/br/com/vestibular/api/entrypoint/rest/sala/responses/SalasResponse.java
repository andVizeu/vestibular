package br.com.vestibular.api.entrypoint.rest.sala.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalasResponse {
    private List<SalaResponse> salas;
}
