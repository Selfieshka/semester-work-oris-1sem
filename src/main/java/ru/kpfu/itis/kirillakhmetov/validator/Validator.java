package ru.kpfu.itis.kirillakhmetov.validator;

public interface Validator<T> {
    ValidationResult isValid(T t);
}
