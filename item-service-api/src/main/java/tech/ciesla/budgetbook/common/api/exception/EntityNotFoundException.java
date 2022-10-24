package tech.ciesla.budgetbook.common.api.exception;

import tech.ciesla.budgetbook.common.api.PlatformException;

import java.util.UUID;

public class EntityNotFoundException extends PlatformException {

    public EntityNotFoundException(UUID id, String message) {
        super(id, message);
    }
}
