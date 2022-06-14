package br.com.vestibular.core.usecase.sala;

import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.exceptions.SalaNotFoundExeption;
import br.com.vestibular.core.exceptions.VestibularNotFoundException;
import br.com.vestibular.core.gateway.SalaGateway;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.core.utils.UuidConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class GetSalaUseCase {

    private final VestibularGateway vestibularGateway;
    private final SalaGateway salaGateway;

    public Sala execute(final GetSalaUseCase.Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        if (!salaGateway.existsSala(request.salaId)) {
            throw new SalaNotFoundExeption(request.salaId);
        }

        return salaGateway.getSala(request.salaId);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        final String vestibularUUID;
        final Long salaId;
    }

}
