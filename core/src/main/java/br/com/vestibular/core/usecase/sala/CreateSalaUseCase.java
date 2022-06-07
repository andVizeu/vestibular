package br.com.vestibular.core.usecase.sala;


import br.com.vestibular.core.domain.Sala;
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
public class CreateSalaUseCase {

    private final VestibularGateway vestibularGateway;
    private final SalaGateway salaGateway;

    public Sala execute(final CreateSalaUseCase.Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        return salaGateway.addSala(Sala.of(request.nome), vestibularUUID);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String vestibularUUID;
        private String nome;
    }

}
