package br.com.vestibular.core.usecase.candidato;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.exceptions.CandidatoNotFoundException;
import br.com.vestibular.core.exceptions.VestibularNotFoundException;
import br.com.vestibular.core.gateway.CandidatoGateway;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.core.utils.UuidConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Component
@AllArgsConstructor
public class GetCandidatoUseCase {

    private final CandidatoGateway candidatoGateway;
    private final VestibularGateway vestibularGateway;

    public Candidato execute(final Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        final Candidato candidato = candidatoGateway.getCandidato(request.candidatoId);
        if (isNull(candidato)) {
            throw new CandidatoNotFoundException(request.candidatoId);
        }

        return candidatoGateway.getCandidato(request.candidatoId);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        final String vestibularUUID;
        final Long candidatoId;
    }

}
