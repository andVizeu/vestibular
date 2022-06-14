package br.com.vestibular.core.gateway;

import br.com.vestibular.core.domain.Candidato;

import java.util.List;
import java.util.UUID;

public interface CandidatoGateway {
    List<Candidato> addCandidato(UUID vestibularUUID, UUID cursoUUID, Candidato candidato);

    List<Candidato> listCandidato(UUID vestibularUUID, UUID cursoUUID);

    Candidato getCandidato(Long candidatoId);

    Candidato updateCandidato(Candidato candidato);

    void deleteCandidato(Long candidatoId);

    boolean existsCandidato(Long candidatoId);
}
