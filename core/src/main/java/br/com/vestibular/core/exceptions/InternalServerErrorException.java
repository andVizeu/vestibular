package br.com.vestibular.core.exceptions;

public class InternalServerErrorException extends BusinessException {
    public InternalServerErrorException() {
        super(Codes.INTERNAL_SERVER_ERROR_EXCEPTION);
    }
}
