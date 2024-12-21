package ru.kpfu.itis.kirillakhmetov.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dto.EmployeeDto;
import ru.kpfu.itis.kirillakhmetov.util.annotations.Singleton;

import java.time.LocalDate;

@Singleton
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateEmployeeValidator implements Validator<EmployeeDto> {
    @Override
    public ValidationResult isValid(EmployeeDto employeeDto) {
        ValidationResult validationResult = new ValidationResult();
        if (employeeDto.firstName().isEmpty()) {
            validationResult.add(new Error("invalid.category", "Поле \"Имя\" не может быть пустым"));
        }
        if (employeeDto.lastName().isEmpty()) {
            validationResult.add(new Error("invalid.category", "Поле \"Фамилия\" не может быть пустым"));
        }
        if (employeeDto.patronymic().isEmpty()) {
            validationResult.add(new Error("invalid.category", "Поле \"Отчество\" не может быть пустым"));
        }
        if (employeeDto.position().isEmpty()) {
            validationResult.add(new Error("invalid.category", "Поле \"Должность\" не может быть пустым"));
        }
        if (employeeDto.effectiveDate().isAfter(LocalDate.now())) {
            validationResult.add(new Error("invalid.category", "Поле \"Дата\" не может быть позже чем сейчас"));
        }
        return validationResult;
    }
}
