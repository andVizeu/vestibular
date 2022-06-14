package br.com.vestibular.core.usecase.candidato;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.exceptions.VestibularNotFoundException;
import br.com.vestibular.core.gateway.CandidatoGateway;
import br.com.vestibular.core.gateway.CursoGateway;
import br.com.vestibular.core.gateway.SalaGateway;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.core.utils.UuidConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class AtribuirCandidatosUseCase {

    private final VestibularGateway vestibularGateway;
    private final CandidatoGateway candidatoGateway;
    private final CursoGateway cursoGateway;
    private final SalaGateway salaGateway;


    public List<Candidato> execute(final Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        final List<Sala> salas = salaGateway.listSalas(vestibularUUID);

        final List<Curso> cursos = cursoGateway.listCursos(vestibularUUID);

        cursos.forEach(curso -> {
            final List<Candidato> candidatos = candidatoGateway.listCandidato(vestibularUUID, curso.getCursoUUID());

            candidatos.forEach(candidato -> {

                salas.forEach(sala -> {

                    for (int i = sala.getCapacidade(); i > 0; i--) {
                        sala.getCandidatos().add(candidato);
                        candidato.setSala(sala);
                        salaGateway.updateSala(sala);
                        candidatoGateway.updateCandidato(candidato);
                    }
                });
            });
        });

        return candidatoGateway.listCandidato(vestibularUUID, null);

    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String vestibularUUID;
    }

}
