package com.ntic.discovery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Cette microservice n'exist aps")
public class NotFoundException extends RuntimeException {
}
