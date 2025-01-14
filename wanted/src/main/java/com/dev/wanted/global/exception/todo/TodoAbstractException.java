package com.dev.wanted.global.exception.todo;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class TodoAbstractException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public TodoAbstractException(String message) {
        super(message);
    }

    public TodoAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
