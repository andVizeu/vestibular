package br.com.vestibular.core.gateway;

import br.com.vestibular.core.domain.Candidato;

import java.util.List;

public interface CandidatoGateway {
    Candidato addCandidato(Candidato candidato);

    List<Candidato> listCandidato();

    Candidato getCandidato(Long candidatoId);

    Candidato updateCandidato(Candidato candidato);

    void deleteCandidato(Long candidatoId);

    boolean existsCandidato(Long candidatoId);
}
