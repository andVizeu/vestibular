package br.com.vestibular.data.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "vestibular")
public class VestibularEntity extends BaseEntity {

    @Generated(GenerationTime.INSERT)
    @Column(name = "vestibular_uuid")
    private UUID vestibularUUID;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "vestibular_curso",
            joinColumns = { @JoinColumn(name = "vestibular_id") },
            inverseJoinColumns = { @JoinColumn(name = "curso_id") }
    )
    private List<CursoEntity> cursos = new ArrayList<>();

    @OneToMany(mappedBy = "vestibular", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SalaEntity> salas;

    @OneToMany(mappedBy = "vestibular", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CandidatoEntity> candidatos;
}
