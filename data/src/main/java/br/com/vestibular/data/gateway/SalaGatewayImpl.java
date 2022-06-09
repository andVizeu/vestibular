package br.com.vestibular.data.gateway;

import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.gateway.SalaGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalaGatewayImpl implements SalaGateway {

    @Override
    public Sala addSala(Sala sala, UUID VestibularUUID) {
        return null;
    }

    @Override
    public List<Sala> listSalas(UUID VestibularUUID) {
        return null;
    }

    @Override
    public Sala getSala(UUID salaUUID, UUID VestibularUUID) {
        return null;
    }

    @Override
    public Sala updateSala(Sala sala, UUID VestibularUUID) {
        return null;
    }

    @Override
    public void deleteSala(UUID salaUUID, UUID VestibularUUID) {

    }

    @Override
    public boolean existsSala(UUID salaUUID) {
        return false;
    }

}
