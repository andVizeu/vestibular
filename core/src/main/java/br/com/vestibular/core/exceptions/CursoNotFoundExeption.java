package br.com.vestibular.core.exceptions;

public class CursoNotFoundExeption extends BusinessException {

    private final String cursoUUID;

    public CursoNotFoundExeption(final String cursoUUID) {
        super(Codes.CURSO_NOT_FOUND_EXCEPTION);
        this.cursoUUID = cursoUUID;
    }

    @Override
    public String getMessage() {
        return String.format("%s - %s", super.getMessage(), cursoUUID);
    }

}
