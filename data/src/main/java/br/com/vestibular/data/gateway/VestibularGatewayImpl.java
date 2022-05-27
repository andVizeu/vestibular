package br.com.vestibular.data.gateway;

import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.data.entity.VestibularEntity;
import br.com.vestibular.data.mapper.DomainToEntityMapper;
import br.com.vestibular.data.mapper.EntityToDomainMapper;
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
public class VestibularGatewayImpl implements VestibularGateway {

    private final VestibularRepository repository;

    private final DomainToEntityMapper toEntityMapper;

    private final EntityToDomainMapper toDomainMapper;


    @Override
    public Vestibular addVestibular(final Vestibular vestibular) {
        final VestibularEntity entity = toEntityMapper.toEntity(vestibular);
        final VestibularEntity savedEntity = repository.save(entity);
        log.info("[VestibularGatewayImpl] Saved vestibular in DB: {}", savedEntity);
        return toDomainMapper.toDomain(savedEntity);
    }

    @Override
    public List<Vestibular> listVestibular() {
        final List<VestibularEntity> entities = repository.findAll();
        log.info("[VestibularGatewayImpl] Number of vestibular recovered from the bank: {}", entities.size());
        return entities.stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Vestibular getVestibular(final UUID vestibularUUID) {
        final Optional<VestibularEntity> optional = repository.findByUUID(vestibularUUID);

        return optional.map(vestibularEntity -> {
            log.info("[VestibularGatewayImpl] Get vestibular from DB: {}", vestibularEntity);
            return toDomainMapper.toDomain(vestibularEntity);
        }).orElse(null);
    }

    @Override
    public Vestibular updateVestibular(final Vestibular vestibular) {
        final Optional<VestibularEntity> optional = repository.findByUUID(vestibular.getVestibularUUID());

        return optional.map(vestibularEntity -> {
            vestibularEntity.update(vestibular);
            final VestibularEntity savedEntity = repository.save(vestibularEntity);
            log.info("[VestibularGatewayImpl] Saved vestibular in DB: {}", savedEntity);
            return toDomainMapper.toDomain(savedEntity);
        }).orElse(null);
    }

    @Override
    public void deleteVestibular(final UUID vestibularUUID) {
        final Optional<VestibularEntity> optional = repository.findByUUID(vestibularUUID);

        if (optional.isPresent()) {
            repository.delete(optional.get());
            log.info("[VestibularGatewayImpl] Deleted vestibular in DB: {}", vestibularUUID);
        }
    }

    @Override
    public boolean existsVestibular(final UUID vestibularUUID) {
        return repository.existsByVestibularUUID(vestibularUUID);
    }
}
