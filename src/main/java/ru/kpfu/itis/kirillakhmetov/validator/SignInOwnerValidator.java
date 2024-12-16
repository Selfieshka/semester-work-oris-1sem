package ru.kpfu.itis.kirillakhmetov.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dto.SignInOwnerDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInOwnerValidator implements Validator<SignInOwnerDto> {
    private static final SignInOwnerValidator _INSTANCE = new SignInOwnerValidator();

    @Override
    public ValidationResult isValid(SignInOwnerDto signInOwnerDto) {
        ValidationResult validationResult = new ValidationResult();
        if (signInOwnerDto.email().isEmpty()) {
            validationResult.add(new Error("invalid.email", "Поле \"Почта\" не может быть пустым"));
        }
        if (signInOwnerDto.password().isEmpty()) {
            validationResult.add(new Error("invalid.password", "Поле \"Пароль\" не может быть пустым"));
        }
        return validationResult;
    }

    public static SignInOwnerValidator getInstance() {
        return _INSTANCE;
    }
}
