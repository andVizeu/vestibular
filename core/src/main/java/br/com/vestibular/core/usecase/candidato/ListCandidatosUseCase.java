package br.com.vestibular.core.usecase.candidato;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.gateway.CandidatoGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ListCandidatosUseCase {

    private final CandidatoGateway candidatoGateway;

    public List<Candidato> execute() {
        return candidatoGateway.listCandidato();
    }
}
