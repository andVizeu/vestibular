package br.com.vestibular.data.repository;

import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.data.entity.CursoEntity;
import br.com.vestibular.data.entity.VestibularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

    Optional<CursoEntity> findByCursoUUID(UUID cursoUUID);

    List<CursoEntity> findAllByVestibulares(VestibularEntity vestibularEntity);

    boolean existsByCursoUUID(UUID cursoUUID);

}
