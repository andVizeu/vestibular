package br.com.vestibular.data.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "sala")
public class SalaEntity extends BaseEntity {

    @Generated(GenerationTime.INSERT)
    @Column(name = "vestibular_uuid")
    private UUID vestibularUUID;

    @Generated(GenerationTime.INSERT)
    @Column(name = "curso_uuid")
    private UUID cursoUUID;

    private String identificador;

    private String bloco;

    private Integer capacidade;

}
