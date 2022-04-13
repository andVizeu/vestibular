package br.com.vestibular.data.repository;

import br.com.vestibular.data.entity.CandidatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoEntity, Long> {

}
