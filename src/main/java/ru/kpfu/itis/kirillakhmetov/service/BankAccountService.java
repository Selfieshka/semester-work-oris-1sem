package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.BankAccountDao;
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
}
