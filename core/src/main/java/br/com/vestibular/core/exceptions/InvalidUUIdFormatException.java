package br.com.vestibular.core.exceptions;

public class InvalidUUIdFormatException extends BusinessException {

    private final String uuid;

    public InvalidUUIdFormatException(final Codes code, final String uuid, final Throwable throwable) {
        super(code, throwable);
        this.uuid = uuid;
    }

    @Override
    public String getMessage() {
        return String.format("%s - %s", super.getMessage(), uuid);
    }

}
