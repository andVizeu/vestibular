package br.com.vestibular.core.gateway;

import br.com.vestibular.core.domain.Candidato;

import java.util.List;
import java.util.UUID;

public interface CandidatoGateway {
    Candidato addCandidato(Candidato candidato);

    List<Candidato> listCandidato();

    Candidato getCandidato(Candidato candidato);

    Candidato updateCandidato(Candidato candidato);

    void deleteCandidato(Candidato candidato);

    boolean existsCandidato(Candidato candidato);
}
