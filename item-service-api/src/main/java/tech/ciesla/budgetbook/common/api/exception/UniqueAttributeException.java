package tech.ciesla.budgetbook.common.api.exception;

import tech.ciesla.budgetbook.common.api.PlatformException;

import java.util.UUID;

public class UniqueAttributeException extends PlatformException {

    public UniqueAttributeException(UUID id, String message) {
        super(id, message);
    }
}
