package br.com.vestibular.data.gateway;

import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.gateway.CursoGateway;
import br.com.vestibular.data.entity.CursoEntity;
import br.com.vestibular.data.entity.VestibularEntity;
import br.com.vestibular.data.mapper.DomainToEntityMapper;
import br.com.vestibular.data.mapper.EntityToDomainMapper;
import br.com.vestibular.data.repository.CursoRepository;
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
public class CursoGatewayImpl implements CursoGateway {

    private final VestibularRepository vestibularRepository;
    private final CursoRepository cursoRepository;
    private final DomainToEntityMapper toEntityMapper;
    private final EntityToDomainMapper toDomainMapper;

    @Override
    public List<Curso> addCurso(Curso curso, UUID vestibularUUID) {
        return vestibularRepository.findByVestibularUUID(vestibularUUID).map(
            vestibularEntity -> {
                final CursoEntity cursoEntity = toEntityMapper.toEntity(curso);
                vestibularEntity.getCursos().add(cursoEntity);
                final VestibularEntity savedVestibular = vestibularRepository.save(vestibularEntity);
                log.info("[CursoGatewayImpl] Saved curso in DB: {}", savedVestibular.getCursos().size());
                return savedVestibular.getCursos().stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
            }
        ).orElse(null);
    }

    @Override
    public List<Curso> listCursos(UUID vestibularUUID) {
        return vestibularRepository.findByVestibularUUID(vestibularUUID).map(
            vestibularEntity -> {
                final List<CursoEntity> cursosEntity = cursoRepository.findAllByVestibulares(vestibularEntity);
                log.info("[CursoGatewayImpl] Number of cursos recovered from the DB: {}", cursosEntity.size());
                return cursosEntity.stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
            }
        ).orElse(null);
    }

    @Override
    public Curso getCurso(UUID cursoUUID) {
        return cursoRepository.findByCursoUUID(cursoUUID).map(
            cursoEntity -> {
                log.info("[CursoGatewayImpl] Get curso from DB: {}", cursoEntity);
                return toDomainMapper.toDomain(cursoEntity);
            }
        ).orElse(null);
    }

    @Override
    public Curso updateCurso(Curso curso) {
        return cursoRepository.findByCursoUUID(curso.getCursoUUID()).map(
            cursoEntity -> {
                final CursoEntity saveEntity = cursoRepository.save(updateCurso(curso, cursoEntity));
                log.info("[CursoGatewayImpl] Saved curso in DB: {}", saveEntity);
                return toDomainMapper.toDomain(saveEntity);
            }
        ).orElse(null);
    }

    @Override
    public List<Curso> deleteCurso(UUID vestibularUUID, UUID cursoUUID) {
        final Optional<VestibularEntity> vestibularOpt = vestibularRepository.findByVestibularUUID(vestibularUUID);
        if (vestibularOpt.isPresent()) {
            final VestibularEntity vestibularEntity = vestibularOpt.get();
            vestibularEntity.getCursos().removeIf(vestibular -> vestibular.getCursoUUID().equals(cursoUUID));
            log.info("[CursoGatewayImpl] Deleted curso in DB: {}", cursoUUID);
            final VestibularEntity savedVestibular = vestibularRepository.save(vestibularEntity);
            return savedVestibular.getCursos().stream().map(toDomainMapper::toDomain).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean existsCurso(UUID cursoUUID) {
        return cursoRepository.existsByCursoUUID(cursoUUID);
    }

    private CursoEntity updateCurso(Curso curso, CursoEntity cursoEntity) {
        cursoEntity.setNome(curso.getNome());
        return cursoEntity;
    }

}
