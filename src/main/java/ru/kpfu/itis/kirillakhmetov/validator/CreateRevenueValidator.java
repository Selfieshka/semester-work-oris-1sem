package ru.kpfu.itis.kirillakhmetov.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dto.FinanceDto;
import ru.kpfu.itis.kirillakhmetov.entity.Finance;
import ru.kpfu.itis.kirillakhmetov.util.annotations.Singleton;

import java.time.LocalDate;

@Singleton
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateRevenueValidator implements Validator<FinanceDto> {
    @Override
    public ValidationResult isValid(FinanceDto financeDto) {
        ValidationResult validationResult = new ValidationResult();
        if  (financeDto.date().isAfter(LocalDate.now())) {
            validationResult.add(new Error("invalid.date", "Поле \"Дата\" не может быть позже, чем текущая дата"));
        }
        if  (financeDto.amount() == 0) {
            validationResult.add(new Error("invalid.amount", "Поле \"Сумма\" не может быть 0"));
        }
        return validationResult;
    }
}
