package br.com.vestibular.data.repository;

import br.com.vestibular.data.entity.SalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<SalaEntity, Long> {

}
