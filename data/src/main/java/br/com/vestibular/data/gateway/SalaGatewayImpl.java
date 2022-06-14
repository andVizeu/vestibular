package br.com.vestibular.data.gateway;


import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.gateway.SalaGateway;
import br.com.vestibular.data.entity.SalaEntity;
import br.com.vestibular.data.entity.VestibularEntity;
import br.com.vestibular.data.mapper.DomainToEntityMapper;
import br.com.vestibular.data.mapper.EntityToDomainMapper;
import br.com.vestibular.data.repository.SalaRepository;
import br.com.vestibular.data.repository.VestibularRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalaGatewayImpl implements SalaGateway {

    private final VestibularRepository vestibularRepository;

    private final SalaRepository salaRepository;
    private final DomainToEntityMapper toEntityMapper;
    private final EntityToDomainMapper toDomainMapper;

    @Override
    public List<Sala> addSala(final Sala sala, final UUID vestibularUUID) {
        return vestibularRepository.findByVestibularUUID(vestibularUUID).map(
                vestibularEntity -> {
                    final SalaEntity salaEntity = toEntityMapper.toEntity(sala);
                    salaEntity.setVestibular(vestibularEntity);
                    vestibularEntity.getSalas().add(salaEntity);
                    final VestibularEntity savedVestibular = vestibularRepository.save(vestibularEntity);
                    log.info("[SalaGatewayImpl] Saved sala in DB: {}", savedVestibular.getSalas().size());
                    return savedVestibular.getSalas().stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
                }
        ).orElse(null);
    }

    @Override
    public List<Sala> listSalas(final UUID vestibularUUID) {
        return vestibularRepository.findByVestibularUUID(vestibularUUID).map(
                vestibularEntity -> {
                    final List<SalaEntity> salasEntity = salaRepository.findAllByVestibular(vestibularEntity);
                    log.info("[SalaGatewayImpl] Number of salas recovered from the DB: {}", salasEntity.size());
                    return salasEntity.stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
                }
        ).orElse(null);
    }

    @Override
    public Sala getSala(final Long salaId) {
        return salaRepository.findById(salaId).map(
                salaEntity -> {
                    log.info("[SalaGatewayImpl] Get sala from DB: {}", salaEntity);
                    return toDomainMapper.toDomain(salaEntity);
                }
        ).orElse(null);
    }

    @Override
    public Sala updateSala(final Sala sala) {
        return salaRepository.findById(sala.getId()).map(
                salaEntity -> {
                    final SalaEntity saveEntity = salaRepository.save(updateSala(sala, salaEntity));
                    log.info("[SalaGatewayImpl] Saved sala in DB: {}", saveEntity);
                    return toDomainMapper.toDomain(saveEntity);
                }
        ).orElse(null);
    }

    @Override
    public List<Sala> deleteSala(final UUID vestibularUUID, final Long salaId) {
        final Optional<VestibularEntity> vestibularOpt = vestibularRepository.findByVestibularUUID(vestibularUUID);
        if (vestibularOpt.isPresent()) {
            final VestibularEntity vestibularEntity = vestibularOpt.get();
            vestibularEntity.getSalas().removeIf(vestibular -> vestibular.getId().equals(salaId));
            log.info("[CursoGatewayImpl] Deleted sala in DB: {}", salaId);
            final VestibularEntity savedVestibular = vestibularRepository.save(vestibularEntity);
            return savedVestibular.getSalas().stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean existsSala(final Long salaId) {
        return salaRepository.existsById(salaId);
    }

    private SalaEntity updateSala(Sala sala, SalaEntity salaEntity) {
        salaEntity.setIdentificador(sala.getIdentificador());
        salaEntity.setBloco(sala.getBloco());
        salaEntity.setCapacidade(sala.getCapacidade());
        return salaEntity;
    }

}
