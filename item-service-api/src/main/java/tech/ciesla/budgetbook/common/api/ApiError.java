package tech.ciesla.budgetbook.common.api;

public record ApiError(
        String id,
        String timestamp,
        String status,
        String error,
        String message,
        String path
) {
}
