package tech.ciesla.budgetbook.common.api;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PlatformException extends RuntimeException {

    private final UUID id;

    protected PlatformException(UUID id, String message, Throwable cause) {
        super(message, cause);

        this.id = id;
    }

    protected PlatformException(UUID id, String message) {
        super(message);

        this.id = id;
    }
}
