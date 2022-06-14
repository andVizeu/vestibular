package br.com.vestibular.data.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "curso")
public class CursoEntity extends BaseEntity {

    private String nome;

    @Generated(GenerationTime.INSERT)
    @Column(name = "curso_uuid")
    private UUID cursoUUID;

    @ManyToMany(mappedBy = "cursos")
    private List<VestibularEntity> vestibulares;

    @OneToMany(mappedBy = "curso")
    private List<CandidatoEntity> candidatos;

}
