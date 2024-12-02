package ru.kpfu.itis.kirillakhmetov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.kirillakhmetov.dao.FinanceDao;
import ru.kpfu.itis.kirillakhmetov.dto.FinanceDto;
import ru.kpfu.itis.kirillakhmetov.dto.PointGraphDto;
import ru.kpfu.itis.kirillakhmetov.entity.Finance;

import java.util.List;

public class FinanceService {
    private final FinanceDao financeDao;

    public FinanceService(FinanceDao financeDao) {
        this.financeDao = financeDao;
    }

    public List<FinanceDto> getInfo() {
        List<Finance> finances = financeDao.findAll();
        return List.of();
    }

    public String getBusinessProfitability() {
        List<PointGraphDto> points =
                financeDao.calculateProfitabilityForEachYear().stream()
                        .map(profitability -> new PointGraphDto(
                                Integer.toString(profitability.getYear()),
                                (int) profitability.getValue()))
                        .toList();
        String jsonObject;
        try {
            jsonObject = new ObjectMapper().writeValueAsString(points);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка перевода данных в json");
        }
        return jsonObject;
    }
}
