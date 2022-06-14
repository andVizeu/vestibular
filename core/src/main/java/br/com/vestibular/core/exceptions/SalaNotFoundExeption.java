package br.com.vestibular.core.exceptions;

public class SalaNotFoundExeption extends BusinessException {

    private final Long salaId;

    public SalaNotFoundExeption(final Long salaId) {
        super(Codes.SALA_NOT_FOUND_EXCEPTION);
        this.salaId = salaId;
    }

    @Override
    public String getMessage() {
        return String.format("%s - %s", super.getMessage(), salaId);
    }

}
