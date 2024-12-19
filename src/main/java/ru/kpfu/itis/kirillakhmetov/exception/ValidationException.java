package ru.kpfu.itis.kirillakhmetov.exception;

import lombok.Getter;
import ru.kpfu.itis.kirillakhmetov.validator.Error;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
