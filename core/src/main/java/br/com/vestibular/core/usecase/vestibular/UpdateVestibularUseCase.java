package br.com.vestibular.core.usecase.vestibular;

import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.exceptions.VestibularNotFoundException;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.core.utils.UuidConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateVestibularUseCase {

    private final VestibularGateway gateway;

    public Vestibular execute(final Request request) {

        final UUID uuid = UuidConverterHelper.convertToUUId(request.vestibularUUID);

        final Vestibular vestibular = gateway.getVestibular(uuid);
        if (isNull(vestibular)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        vestibular.update(request);
        return gateway.updateVestibular(vestibular);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String vestibularUUID;
        private LocalDateTime dataInicio;
        private LocalDateTime dataFim;
    }

}
