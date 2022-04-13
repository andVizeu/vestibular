package br.com.vestibular.data.repository;

import br.com.vestibular.data.entity.VestibularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VestibularRepository extends JpaRepository<VestibularEntity, Long> {

}
