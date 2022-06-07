package br.com.vestibular.data.gateway;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.gateway.CandidatoGateway;
import br.com.vestibular.data.entity.CandidatoEntity;
import br.com.vestibular.data.mapper.DomainToEntityMapper;
import br.com.vestibular.data.mapper.EntityToDomainMapper;
import br.com.vestibular.data.repository.CandidatoRepository;
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
public class CandidatoGatewayImpl implements CandidatoGateway {

    private final CandidatoRepository repository;

    private final DomainToEntityMapper toEntityMapper;

    private final EntityToDomainMapper toDomainMapper;


    @Override
    public Candidato addCandidato(final Candidato candidato) {
        final CandidatoEntity entity = toEntityMapper.toEntity(candidato);
        final CandidatoEntity savedEntity = repository.save(entity);
        log.info("[CandidatoGatewayImpl] Saved candidato in DB: {}", savedEntity);
        return toDomainMapper.toDomain(savedEntity);
    }

    @Override
    public List<Candidato> listCandidato() {
        final List<CandidatoEntity> entities = repository.findAll();
        log.info("[CandidatoGatewayImpl] Number of candidato recovered from the bank: {}", entities.size());
        return entities.stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Candidato getCandidato(final UUID candidatoUUID) {
        final Optional<CandidatoEntity> optional = repository.findByCandidatoUUID(candidatoUUID);

        return optional.map(candidatoEntity -> {
            log.info("[CandidatoGatewayImpl] Get candidato from DB: {}", candidatoEntity);
            return toDomainMapper.toDomain(candidatoEntity);
        }).orElse(null);
    }

    @Override
    public Candidato updateCandidato(final Candidato candidato) {
        final Optional<CandidatoEntity> optional = repository.findByCandidatoUUID(candidato.getCandidatoUUID());

        return optional.map(candidatoEntity -> {
            candidatoEntity.update(candidato);
            final CandidatoEntity savedEntity = repository.save(candidatoEntity);
            log.info("[CandidatoGatewayImpl] Saved candidato in DB: {}", savedEntity);
            return toDomainMapper.toDomain(savedEntity);
        }).orElse(null);
    }

    @Override
    public void deleteCandidato(final UUID candidatoUUID) {
        final Optional<CandidatoEntity> optional = repository.findByCandidatoUUID(candidatoUUID);

        if (optional.isPresent()) {
            repository.delete(optional.get());
            log.info("[CandidatoGatewayImpl] Deleted vestibular in DB: {}", candidatoUUID);
        }
    }

    @Override
    public boolean existsCandidato(final UUID candidatoUUID) {
        return repository.existsByCandidatoUUID(candidatoUUID);
    }
}
