package br.com.vestibular.core.usecase.vestibular;

import br.com.vestibular.core.domain.Vestibular;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CreateVestibularUseCase {

    public Vestibular execute(final Request request) {
        return null;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {

    }

}
