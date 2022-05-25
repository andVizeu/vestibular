package br.com.vestibular.data.gateway;

import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.data.repository.VestibularRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class VestibularGatewayImpl implements VestibularGateway {

    private final VestibularRepository vestibularRepository;


    @Override
    public Vestibular addVestibular(final Vestibular vestibular) {
        return null;
    }

    @Override
    public List<Vestibular> listVestibular() {
        return null;
    }

    @Override
    public Vestibular getVestibular(final UUID vestibularUUID) {
        return null;
    }

    @Override
    public Vestibular updateVestibular(final Vestibular vestibular) {
        return null;
    }

    @Override
    public void deleteVestibular(final UUID vestibularUUID) {

    }

    @Override
    public boolean existsVestibular(final UUID vestibularUUID) {
        return false;
    }
}
