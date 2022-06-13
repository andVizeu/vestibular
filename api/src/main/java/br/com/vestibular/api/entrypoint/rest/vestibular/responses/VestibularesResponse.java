package br.com.vestibular.api.entrypoint.rest.vestibular.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VestibularesResponse {
    private List<VestibularResponse> vestibulares;
}
