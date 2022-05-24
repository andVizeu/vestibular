package br.com.vestibular.api.entrypoint.rest.vestibular.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VestibularesResponse {
    private List<VestibularResponse> vestibulares;
}
