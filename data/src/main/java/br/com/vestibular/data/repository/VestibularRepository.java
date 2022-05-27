package br.com.vestibular.data.repository;

import br.com.vestibular.data.entity.VestibularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VestibularRepository extends JpaRepository<VestibularEntity, Long> {

    Optional<VestibularEntity> findByUUID(UUID vestibularUUID);

    boolean existsByVestibularUUID(UUID vestibularUUID);

}
