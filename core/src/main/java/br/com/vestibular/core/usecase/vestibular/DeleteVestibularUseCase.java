package br.com.vestibular.core.usecase.vestibular;

import br.com.vestibular.core.exceptions.VestibularNotFoundException;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.core.utils.UuidConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class DeleteVestibularUseCase {

    private final VestibularGateway gateway;

    public void execute(final Request request) {

        final UUID uuid = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!gateway.existsVestibular(uuid)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        gateway.deleteVestibular(uuid);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String vestibularUUID;
    }

}
