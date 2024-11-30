package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.MonetaryAccountingDao;
import ru.kpfu.itis.kirillakhmetov.dto.MonetaryAccountingDto;
import ru.kpfu.itis.kirillakhmetov.entity.MonetaryAccounting;

import java.util.List;

public class MonetaryAccountingService {
    private final MonetaryAccountingDao monetaryAccountingDao;

    public MonetaryAccountingService(MonetaryAccountingDao monetaryAccountingDao) {
        this.monetaryAccountingDao = monetaryAccountingDao;
    }

    public List<MonetaryAccountingDto> getInfo() {
        List<MonetaryAccounting> monetaryAccountings = monetaryAccountingDao.findAll();
        return List.of();
    }
}
