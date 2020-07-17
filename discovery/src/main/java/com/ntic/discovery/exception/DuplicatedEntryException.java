package com.ntic.discovery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Cette addresse et est exisy déja dans meme version")
public class DuplicatedEntryException extends RuntimeException {
}
