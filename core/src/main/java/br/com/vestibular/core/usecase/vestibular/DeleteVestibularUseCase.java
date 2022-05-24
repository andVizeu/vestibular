package br.com.vestibular.core.usecase.vestibular;

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

    public void execute(final Request request) {

    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private UUID vestibularUUID;
    }

}
