package br.com.vestibular.core.exceptions;

public class CandidatoNotFoundException extends BusinessException {

    private final Long candidatoId;

    public CandidatoNotFoundException(final Long candidatoId) {
        super(Codes.CANDIDATO_NOT_FOUND_EXCEPTION);
        this.candidatoId = candidatoId;
    }

    @Override
    public String getMessage() {
        return String.format("%s - %s", super.getMessage(), candidatoId);
    }

}
