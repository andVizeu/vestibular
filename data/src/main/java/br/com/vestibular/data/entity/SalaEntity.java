package br.com.vestibular.data.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "sala")
public class SalaEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vestibular_id")
    private VestibularEntity vestibular;

    @Generated(GenerationTime.INSERT)
    @Column(name = "curso_uuid")
    private UUID cursoUUID;

    private String identificador;

    private String bloco;

    private Integer capacidade;

}
