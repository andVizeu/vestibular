package br.com.vestibular.core.exceptions;

public class VestibularNotFoundException extends BusinessException {

    private final String vestibularUUID;

    public VestibularNotFoundException(final String vestibularUUID) {
        super(Codes.VESTIBULAR_NOT_FOUND_EXCEPTION);
        this.vestibularUUID = vestibularUUID;
    }

    @Override
    public String getMessage() {
        return String.format("%s - %s", super.getMessage(), vestibularUUID);
    }

}
