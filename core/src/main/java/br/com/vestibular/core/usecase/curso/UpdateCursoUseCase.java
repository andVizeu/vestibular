package br.com.vestibular.core.usecase.curso;

import br.com.vestibular.core.domain.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateCursoUseCase {

    public Curso execute(final Request request) {
        return null;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String nome;
        private UUID cursoUUID;
    }

}
