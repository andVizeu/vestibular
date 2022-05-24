package br.com.vestibular.core.usecase.sala;

import br.com.vestibular.core.domain.Sala;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateSalaUseCase {

    public Sala execute(final Request request) {
        return null;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {

    }

}
