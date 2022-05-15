package br.com.vestibular.data.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "candidato")
public class CandidatoEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vestibular_id")
    private VestibularEntity vestibular;

    private String nome;

    private LocalDateTime dataNascimento;

    private String cpf;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sala_id")
    private SalaEntity sala;

}
