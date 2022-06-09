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

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class CreateCandidatoUseCase {

    private final CandidatoGateway candidatoGateway;
    private final VestibularGateway vestibularGateway;
    private final CursoGateway cursoGateway;

    public Candidato execute(final Request request) {

        final UUID vestibularUUID = UuidConverterHelper.convertToUUId(request.vestibularUUID);
        if (!vestibularGateway.existsVestibular(vestibularUUID)) {
            throw new VestibularNotFoundException(request.vestibularUUID);
        }

        final UUID cursoUUID = UuidConverterHelper.convertToUUId(request.cursoUUID);
        if (!cursoGateway.existsCurso(cursoUUID)) {
            throw new CursoNotFoundExeption(request.cursoUUID);
        }

        return candidatoGateway.addCandidato(Candidato.of(request.nome, request.dataNascimento, request.cpf));
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String vestibularUUID;
        private String cursoUUID;
        private String nome;
        private LocalDateTime dataNascimento;
        private String cpf;
    }

}
