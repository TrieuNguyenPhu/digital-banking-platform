package io.github.trieunguyenphu.digitalbanking.shared.api;

import java.util.List;

public record ApiErrorResponse(int status, String code, String message, List<ApiFieldError> errors) {
}
