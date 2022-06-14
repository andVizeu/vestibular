package br.com.vestibular.core.gateway;


import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Sala;

import java.util.List;
import java.util.UUID;

public interface SalaGateway {

    Sala addSala(Sala sala, UUID vestibularUUID);

    List<Sala> listSalas(UUID vestibularUUID);

    Sala getSala(Long salaId);

    Sala updateSala(Sala sala);

    void deleteSala(Long salaId);

    boolean existsSala(Long salaId);
}
