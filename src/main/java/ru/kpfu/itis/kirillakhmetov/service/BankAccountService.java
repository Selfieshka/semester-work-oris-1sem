package ru.kpfu.itis.kirillakhmetov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.kirillakhmetov.dao.BankAccountDao;
import ru.kpfu.itis.kirillakhmetov.dto.ApiFinanceDto;
import ru.kpfu.itis.kirillakhmetov.dto.BankAccountDto;
import ru.kpfu.itis.kirillakhmetov.entity.BankAccount;

public class BankAccountService {
    private final BankAccountDao bankAccountDao;

    public BankAccountService(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    public void addAccount(BankAccountDto bankAccountDto) {
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
