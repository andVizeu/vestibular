package br.com.vestibular.data.repository;


import br.com.vestibular.data.entity.SalaEntity;
import br.com.vestibular.data.entity.VestibularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaRepository extends JpaRepository<SalaEntity, Long> {
    Optional<SalaEntity> findBySalaUUID(UUID salaUUID);

    List<SalaEntity> findAllByVestibulares(VestibularEntity vestibularEntity);

    boolean existsBySalaUUID(UUID salaUUID);
}
