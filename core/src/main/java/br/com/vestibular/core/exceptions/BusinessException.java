package br.com.vestibular.core.exceptions;

import java.util.Objects;

public abstract class BusinessException extends RuntimeException {
    protected final Codes code;

    public BusinessException(final Codes code) {
        super();
        Objects.requireNonNull(code);
        this.code = code;
    }

    public BusinessException(final Codes code, final Throwable throwable) {
        super(throwable);
        Objects.requireNonNull(code);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.code.getMessage();
    }

    public long getCode() {
        return this.code.getCode();
    }

    public enum Codes {
        /*
         * 4000 to 4999 codes are from BAD_REQUEST (HTTP 400 Statuses)
         */

        UNKNOWN_EXCEPTION(4000, "An unknown exception was thrown."),

        INVALID_UUID_FORMAT(4001, "Invalid uuid format"),

        INTERNAL_SERVER_ERROR_EXCEPTION(5000, "An internal server error was thrown.");

        private long code;
        private String message;

        Codes(final long code, final String message) {
            Objects.requireNonNull(message);
            this.code = code;
            this.message = message;
        }

        public long getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
