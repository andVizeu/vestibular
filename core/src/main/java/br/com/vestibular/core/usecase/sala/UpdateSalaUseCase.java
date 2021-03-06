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

import static java.util.Objects.isNull;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateSalaUseCase {

    private final VestibularGateway vestibularGateway;
    private final SalaGateway salaGateway;

    public Sala execute(final UpdateSalaUseCase.Request request) {
    final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
        throw new VestibularNotFoundException(request.vestibularUUID);
    }

    final Sala sala = salaGateway.getSala(request.salaId);
        if (isNull(sala)) {
        throw new SalaNotFoundExeption(request.salaId);
    }

        sala.update(request);
        return salaGateway.updateSala(sala);
}

@Setter
@Getter
@AllArgsConstructor
public static class Request {
    private String identificador;
    private String bloco;
    private Integer capacidade;
    private String vestibularUUID;
    private Long salaId;
}

}
