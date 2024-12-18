package ru.kpfu.itis.kirillakhmetov.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dto.SignUpOwnerDto;
import ru.kpfu.itis.kirillakhmetov.util.annotations.Singleton;

@Singleton
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpOwnerValidator implements Validator<SignUpOwnerDto> {
    @Override
    public ValidationResult isValid(SignUpOwnerDto signUpOwnerDto) {
        ValidationResult validationResult = new ValidationResult();
        if (signUpOwnerDto.firstName().isEmpty()) {
            validationResult.add(new Error("invalid.firstname", "Поле \"Имя\" не может быть пустым"));
        }
        if (signUpOwnerDto.email().isEmpty()) {
            validationResult.add(new Error("invalid.email", "Поле \"Почта\" не может быть пустым"));
        }
        if (signUpOwnerDto.businessName().isEmpty()) {
            validationResult.add(new Error("invalid.business", "Поле \"Название бизнеса\" не может быть пустым"));
        }
        if (signUpOwnerDto.password().isEmpty()) {
            validationResult.add(new Error("invalid.password", "Поле \"Пароль\" не может быть пустым"));
        }
        return validationResult;
    }
}
