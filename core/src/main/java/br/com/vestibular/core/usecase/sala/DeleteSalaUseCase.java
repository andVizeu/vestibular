package br.com.vestibular.core.usecase.sala;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DeleteSalaUseCase {

    public void execute(final Request request) {

    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {

    }

}
