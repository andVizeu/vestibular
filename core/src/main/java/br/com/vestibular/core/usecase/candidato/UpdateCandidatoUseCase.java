package br.com.vestibular.core.usecase.candidato;

import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.exceptions.CandidatoNotFoundException;
import br.com.vestibular.core.exceptions.CursoNotFoundExeption;
import br.com.vestibular.core.exceptions.VestibularNotFoundException;
import br.com.vestibular.core.gateway.CandidatoGateway;
import br.com.vestibular.core.gateway.CursoGateway;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.core.utils.UuidConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateCandidatoUseCase {

    private final VestibularGateway vestibularGateway;
    private final CursoGateway cursoGateway;
    private final CandidatoGateway candidatoGateway;

    public Candidato execute(final Request request) {


        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        final UUID cursoUUID = UuidConverterHelper.convertToUUId(request.cursoUUID);
        final Curso curso = cursoGateway.getCurso(cursoUUID);
        if (isNull(curso)) {
            throw new CursoNotFoundExeption(request.cursoUUID);
        }

        final Candidato candidato = candidatoGateway.getCandidato(request.candidatoId);
        if (isNull(candidato)) {
            throw new CandidatoNotFoundException(request.candidatoId);
        }

        candidato.update(request);
        return candidatoGateway.updateCandidato(candidato);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String cursoUUID;
        private String vestibularUUID;
        private Long candidatoId;
        private String nome;
        private LocalDateTime dataNascimento;
        private String cpf;
    }

}
