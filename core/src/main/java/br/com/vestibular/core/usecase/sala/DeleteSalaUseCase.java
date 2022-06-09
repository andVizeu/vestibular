package br.com.vestibular.core.usecase.sala;

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
public class DeleteSalaUseCase {

    private final VestibularGateway vestibularGateway;
    private final SalaGateway salaGateway;

    public void execute(final DeleteSalaUseCase.Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        final UUID salaUUID = UuidConverterHelper.convertToUUId(request.salaUUID);
        if (!salaGateway.existsSala(salaUUID)) {
            throw new SalaNotFoundExeption(request.salaUUID);
        }

        salaGateway.deleteSala(vestibularUUID, salaUUID);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String vestibularUUID;
        private String salaUUID;
    }

}