package ru.kpfu.itis.kirillakhmetov.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dto.BankAccountDto;
import ru.kpfu.itis.kirillakhmetov.util.annotations.Singleton;

@Singleton
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateBankAccountValidator implements Validator<BankAccountDto> {
    @Override
    public ValidationResult isValid(BankAccountDto bankAccountDto) {
        ValidationResult validationResult = new ValidationResult();
        if (bankAccountDto.amount() == 0) {
            validationResult.add(new Error("invalid.amount", "Поле \"Сумма\" не может быть 0"));
        }
        if (bankAccountDto.bankName().isEmpty()) {
            validationResult.add(new Error("invalid.bankName", "Поле \"Банк\" не может быть пустым"));
        }
        return validationResult;
    }
}
