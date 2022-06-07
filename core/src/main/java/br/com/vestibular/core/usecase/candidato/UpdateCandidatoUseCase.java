package br.com.vestibular.core.usecase.candidato;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.gateway.CandidatoGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateCandidatoUseCase {

    private final CandidatoGateway candidatoGateway;

    public Candidato execute(final Request request) {

        final idCandidato = UuidConverterHelper.convertToUUId(request.candidato);

        final Candidato candidato = candidatoGateway.getCandidato(idCandidato);
        if (isNull(candidato)) {
            throw new CandidatoNotFoundException(request.candidato);
        }

        candidato.update(request);
        return candidatoGateway.updateCandidato(candidato);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String nome;
        private LocalDateTime dataNascimento;
        private String cpf;
    }

}
