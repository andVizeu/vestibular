package br.com.vestibular.data.repository;

import br.com.vestibular.data.entity.CandidatoEntity;
import br.com.vestibular.data.entity.CursoEntity;
import br.com.vestibular.data.entity.VestibularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoEntity, Long> {

    boolean existsById(Long id);

    List<CandidatoEntity> findByVestibular(VestibularEntity vestibularEntity);

    List<CandidatoEntity> findByVestibularAndCurso(VestibularEntity vestibularEntity, CursoEntity cursoEntity);

}
