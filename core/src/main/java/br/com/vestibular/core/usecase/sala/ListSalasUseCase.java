package br.com.vestibular.core.usecase.sala;

import br.com.vestibular.core.domain.Sala;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ListSalasUseCase {

    public List<Sala> execute(final Request request) {
        return null;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

    }

}
