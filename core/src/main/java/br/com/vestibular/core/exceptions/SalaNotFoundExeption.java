package br.com.vestibular.core.exceptions;

public class SalaNotFoundExeption extends BusinessException {

    private final String salaUUID;

    public SalaNotFoundExeption(final String salaUUID) {
        super(Codes.SALA_NOT_FOUND_EXCEPTION);
        this.salaUUID = salaUUID;
    }

    @Override
    public String getMessage() {
        return String.format("%s - %s", super.getMessage(), salaUUID);
    }

}
