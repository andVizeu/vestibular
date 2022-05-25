package br.com.vestibular.core.gateway;

import br.com.vestibular.core.domain.Vestibular;

import java.util.List;
import java.util.UUID;

public interface VestibularGateway {

    Vestibular addVestibular(Vestibular vestibular);

    List<Vestibular> listVestibular();

    Vestibular getVestibular(UUID vestibularUUID);

    Vestibular updateVestibular(Vestibular vestibular);

    void deleteVestibular(UUID vestibularUUID);

    boolean existsVestibular(UUID vestibularUUID);

}
