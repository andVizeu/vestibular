package br.com.vestibular.core.utils;

import br.com.vestibular.core.exceptions.BusinessException;
import br.com.vestibular.core.exceptions.InvalidUUIdFormatException;

import java.util.UUID;

public class UuidConverterHelper {

    private UuidConverterHelper() {
    }

    public static UUID convertToUUId(final String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (final IllegalArgumentException exception) {
            throw new InvalidUUIdFormatException(BusinessException.Codes.INVALID_UUID_FORMAT, uuid, exception);
        }
    }

}
