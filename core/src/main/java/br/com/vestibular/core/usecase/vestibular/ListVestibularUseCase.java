package br.com.vestibular.core.usecase.vestibular;

import br.com.vestibular.core.domain.Vestibular;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ListVestibularUseCase {

    public Vestibular execute(final CreateVestibularUseCase.Request request) {
        return null;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

    }

}
