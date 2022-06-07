package br.com.vestibular.core.gateway;


import br.com.vestibular.core.domain.Sala;

import java.util.List;
import java.util.UUID;

public interface SalaGateway {

    Sala addSala(Sala sala, UUID VestibularUUID);

    List<Sala> listSalas(UUID VestibularUUID);

    Sala getSala(UUID salaUUID, UUID VestibularUUID);

    Sala updateSala(Sala sala, UUID VestibularUUID);

    void deleteSala(UUID salaUUID, UUID VestibularUUID);

    boolean existsSala(UUID salaUUID);
}
