package br.com.vestibular.core.usecase.candidato;

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

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class DeleteCandidatoUseCase {

    private final CandidatoGateway candidatoGateway;
    private final VestibularGateway vestibularGateway;
    private final CursoGateway cursoGateway;

    public void execute(final Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        final  UUID cursoUUID = UuidConverterHelper.convertToUUId(request.cursoUUID);
        if (!cursoGateway.existsCurso(cursoUUID)) {
            throw new CursoNotFoundExeption(request.cursoUUID);
        }

        candidatoGateway.deleteCandidato(request.candidatoId);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        final String vestibularUUID;
        final String cursoUUID;
        final Long candidatoId;
    }

}
