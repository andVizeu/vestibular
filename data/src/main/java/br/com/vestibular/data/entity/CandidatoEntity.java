package br.com.vestibular.data.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "candidato")
public class CandidatoEntity extends BaseEntity {

    @Generated(GenerationTime.INSERT)
    @Column(name = "vestibular_uuid")
    private UUID vestibularUUID;

    private String nome;

    private LocalDateTime dataNascimento;

    private String cpf;

    @OneToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @OneToOne
    @JoinColumn(name = "sala_id")
    private SalaEntity sala;

}
