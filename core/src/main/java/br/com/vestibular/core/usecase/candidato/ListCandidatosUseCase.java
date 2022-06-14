package br.com.vestibular.core.usecase.candidato;

import br.com.vestibular.core.domain.Candidato;
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

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@AllArgsConstructor
public class ListCandidatosUseCase {

    private final VestibularGateway vestibularGateway;

    private final CursoGateway cursoGateway;
    private final CandidatoGateway candidatoGateway;

    public List<Candidato> execute(final Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        UUID cursoUUID = null;
        if (nonNull(request.cursoUUID)) {
            cursoUUID = UuidConverterHelper.convertToUUId(request.cursoUUID);
            if (!cursoGateway.existsCurso(cursoUUID)) {
                throw new CursoNotFoundExeption(request.cursoUUID);
            }
        }

        return candidatoGateway.listCandidato(vestibularUUID, cursoUUID);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String vestibularUUID;
        private String cursoUUID;
    }

}
