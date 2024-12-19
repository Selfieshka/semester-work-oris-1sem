package ru.kpfu.itis.kirillakhmetov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dao.BankAccountDao;
import ru.kpfu.itis.kirillakhmetov.dto.ApiFinanceDto;
import ru.kpfu.itis.kirillakhmetov.dto.BankAccountDto;
import ru.kpfu.itis.kirillakhmetov.entity.BankAccount;
import ru.kpfu.itis.kirillakhmetov.exception.ValidationException;
import ru.kpfu.itis.kirillakhmetov.util.annotations.SingletonFactory;
import ru.kpfu.itis.kirillakhmetov.validator.CreateBankAccountValidator;
import ru.kpfu.itis.kirillakhmetov.validator.ValidationResult;

@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountDao bankAccountDao;
    private final static CreateBankAccountValidator createBankAccountValidator = SingletonFactory.getInstance(CreateBankAccountValidator.class);

    public void addAccount(BankAccountDto bankAccountDto) throws ValidationException {
        ValidationResult validationResult = createBankAccountValidator.isValid(bankAccountDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        bankAccountDao.save(BankAccount.builder()
                .ownerId(bankAccountDto.owner_id())
                .bankName(bankAccountDto.bankName())
                .amount(bankAccountDto.amount())
                .build());
    }

    public String calculateAllAmount(Long idOwner) {
        double sumMoney = bankAccountDao.sumAllAmount(idOwner);
        String jsonObject;
        try {
            jsonObject = new ObjectMapper().writeValueAsString(new ApiFinanceDto(sumMoney));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка перевода данных в json");
        }
        return jsonObject;
    }
}
