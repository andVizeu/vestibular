package br.com.vestibular.core.usecase.vestibular;

import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.gateway.VestibularGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ListVestibularUseCase {

    private final VestibularGateway gateway;

    public List<Vestibular> execute() {
        return gateway.listVestibular();
    }

}
