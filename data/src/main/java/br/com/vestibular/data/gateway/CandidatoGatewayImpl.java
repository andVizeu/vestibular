package br.com.vestibular.data.gateway;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.gateway.CandidatoGateway;
import br.com.vestibular.data.entity.CandidatoEntity;
import br.com.vestibular.data.entity.CursoEntity;
import br.com.vestibular.data.entity.VestibularEntity;
import br.com.vestibular.data.mapper.DomainToEntityMapper;
import br.com.vestibular.data.mapper.EntityToDomainMapper;
import br.com.vestibular.data.repository.CandidatoRepository;
import br.com.vestibular.data.repository.CursoRepository;
import br.com.vestibular.data.repository.VestibularRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class CandidatoGatewayImpl implements CandidatoGateway {

    private final VestibularRepository vestibularRepository;

    private final CursoRepository cursoRepository;
    private final CandidatoRepository candidatoRepository;

    private final DomainToEntityMapper toEntityMapper;

    private final EntityToDomainMapper toDomainMapper;


    @Override
    public List<Candidato> addCandidato(final UUID vestibularUUID, final UUID cursoUUID, final Candidato candidato) {
        final Optional<VestibularEntity> vestibularOpt = vestibularRepository.findByVestibularUUID(vestibularUUID);
        final Optional<CursoEntity> cursoOpt = cursoRepository.findByCursoUUID(cursoUUID);

        if (vestibularOpt.isPresent() && cursoOpt.isPresent()) {
            final CandidatoEntity candidatoEntity = toEntityMapper.toEntity(candidato);
            candidatoEntity.setVestibular(vestibularOpt.get());
            candidatoEntity.setCurso(cursoOpt.get());
            final CandidatoEntity savedCandidato = candidatoRepository.save(candidatoEntity);
            log.info("[CandidatoGatewayImpl] Saved candidato in DB: {}", savedCandidato);
            final List<CandidatoEntity> candidatos = candidatoRepository.findByVestibularAndCurso(vestibularOpt.get(), cursoOpt.get());
            return candidatos.stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Candidato> listCandidato(final UUID vestibularUUID, final UUID cursoUUID) {
        List<CandidatoEntity> candidatoEntitiesList = null;

        final Optional<VestibularEntity> vestibularOpt = vestibularRepository.findByVestibularUUID(vestibularUUID);

        if (vestibularOpt.isPresent()) {
            if (isNull(cursoUUID)) {
                candidatoEntitiesList = candidatoRepository.findByVestibular(vestibularOpt.get());
            } else {
                final Optional<CursoEntity> cursoOpt = cursoRepository.findByCursoUUID(cursoUUID);
                candidatoEntitiesList = candidatoRepository.findByVestibularAndCurso(vestibularOpt.get(), cursoOpt.get());
            }
        }

        if (nonNull(candidatoEntitiesList)) {
            return candidatoEntitiesList.stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Candidato getCandidato(final Long candidatoId) {
        final Optional<CandidatoEntity> optional = candidatoRepository.findById(candidatoId);

        return optional.map(candidatoEntity -> {
            log.info("[CandidatoGatewayImpl] Get candidato from DB: {}", candidatoEntity);
            return toDomainMapper.toDomain(candidatoEntity);
        }).orElse(null);
    }

    @Override
    public Candidato updateCandidato(final Candidato candidato) {
        final Optional<CandidatoEntity> optional = candidatoRepository.findById(candidato.getId());

        return optional.map(candidatoEntity -> {
            final CandidatoEntity savedEntity = candidatoRepository.save(update(candidato, candidatoEntity));
            log.info("[CandidatoGatewayImpl] Saved candidato in DB: {}", savedEntity);
            return toDomainMapper.toDomain(savedEntity);
        }).orElse(null);
    }

    @Override
    public void deleteCandidato(final Long candidatoId) {
        final Optional<CandidatoEntity> optional = candidatoRepository.findById(candidatoId);
        optional.ifPresent(candidatoRepository::delete);
        log.info("[CandidatoGatewayImpl] Deleted candidato in DB: {}", candidatoId);
    }

    @Override
    public boolean existsCandidato(final Long candidatoId) {
        return candidatoRepository.existsById(candidatoId);
    }

    private CandidatoEntity update(final Candidato candidato, final CandidatoEntity candidatoEntity) {
        candidatoEntity.setNome(candidato.getNome());
        candidatoEntity.setDataNascimento(candidato.getDataNascimento());
        candidatoEntity.setCpf(candidato.getCpf());
        candidatoEntity.setSala(toEntityMapper.toEntity(candidato.getSala()));
        return candidatoEntity;
    }

}
