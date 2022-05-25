package br.com.vestibular.core.usecase.vestibular;

import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.gateway.VestibularGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class CreateVestibularUseCase {

    private final VestibularGateway gateway;

    public Vestibular execute(final Request request) {
        return gateway.addVestibular(Vestibular.of(request.dataInicio, request.dataFim));
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private LocalDateTime dataInicio;
        private LocalDateTime dataFim;
    }

}
