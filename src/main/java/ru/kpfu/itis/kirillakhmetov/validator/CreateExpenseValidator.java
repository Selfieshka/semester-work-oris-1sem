package ru.kpfu.itis.kirillakhmetov.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dto.FinanceDto;
import ru.kpfu.itis.kirillakhmetov.util.annotations.Singleton;

import java.time.LocalDate;

@Singleton
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateExpenseValidator implements Validator<FinanceDto> {
    @Override
    public ValidationResult isValid(FinanceDto financeDto) {
        ValidationResult validationResult = new ValidationResult();
        if (financeDto.amount() < 0 || financeDto.amount() >= 9999999999D) {
            validationResult.add(new Error("invalid.amount", "Поле \"Сумма\" вне пределов допустимой нормы"));
        }
        if (financeDto.date().isAfter(LocalDate.now())) {
            validationResult.add(new Error("invalid.date", "Поле \"Дата\" не может быть позже, чем текущая дата"));
        }
        if (financeDto.amount() == 0) {
            validationResult.add(new Error("invalid.amount", "Поле \"Сумма\" не может быть 0"));
        }
        if (financeDto.category().isEmpty()) {
            validationResult.add(new Error("invalid.category", "Поле \"Категория\" не может быть пустым"));
        }
        return validationResult;
    }
}
