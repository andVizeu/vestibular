package br.com.vestibular.data.gateway;


import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.gateway.SalaGateway;

import br.com.vestibular.data.entity.SalaEntity;
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
    public Sala addSala(Sala sala, UUID vestibularUUID) {
        return vestibularRepository.findByVestibularUUID(vestibularUUID).map(
                vestibularEntity -> {
                    final SalaEntity salaEntity = toEntityMapper.toEntity(sala);
                    vestibularEntity.getSalas().add(salaEntity);
                    final SalaEntity savedEntity = salaRepository.save(salaEntity);
                    log.info("[SalaGatewayImpl] Saved sala in DB: {}", savedEntity);
                    return toDomainMapper.toDomain(savedEntity);
                }
        ).orElse(null);
    }

    @Override
    public List<Sala> listSalas(UUID vestibularUUID) {
        return vestibularRepository.findByVestibularUUID(vestibularUUID).map(
                vestibularEntity -> {
                    final List<SalaEntity> salasEntity = salaRepository.findAllByVestibulares(vestibularEntity);
                    log.info("[SalaGatewayImpl] Number of salas recovered from the DB: {}", salasEntity.size());
                    return salasEntity.stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
                }
        ).orElse(null);
    }

    @Override
    public Sala getSala(UUID salaUUID) {
        return salaRepository.findBySalaUUID(salaUUID).map(
                salaEntity -> {
                    log.info("[SalaGatewayImpl] Get sala from DB: {}", salaEntity);
                    return toDomainMapper.toDomain(salaEntity);
                }
        ).orElse(null);
    }

    @Override
    public Sala updateSala(Sala sala) {
        return salaRepository.findBySalaUUID(sala.getSalaUUID()).map(
                salaEntity -> {
                    final SalaEntity saveEntity = salaRepository.save(updateSala(sala, salaEntity));
                    log.info("[SalaGatewayImpl] Saved sala in DB: {}", saveEntity);
                    return toDomainMapper.toDomain(saveEntity);
                }
        ).orElse(null);
    }

    @Override
    public void deleteSala(UUID salaUUID) {
        final Optional<SalaEntity> salaEntityOpt = salaRepository.findBySalaUUID(salaUUID);
        salaEntityOpt.ifPresent(salaRepository::delete);
        log.info("[SalaGatewayImpl] Deleted sala in DB: {}", salaUUID);
    }

    @Override
    public boolean existsSala(UUID salaUUID) {
        return salaRepository.existsBySalaUUID(salaUUID);
    }

    private SalaEntity updateSala(Sala sala, SalaEntity salaEntity) {
        salaEntity.setIdentificador(sala.getIdentificador());
        return salaEntity;
    }

}
