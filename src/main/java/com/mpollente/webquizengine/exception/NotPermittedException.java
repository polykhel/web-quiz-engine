package com.mpollente.webquizengine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "This operation is forbidden")
public class NotPermittedException extends RuntimeException {
}
