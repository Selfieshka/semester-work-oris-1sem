package ru.kpfu.itis.kirillakhmetov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.kirillakhmetov.dao.AnalyticsDao;
import ru.kpfu.itis.kirillakhmetov.dto.ProfitabilityRecordDto;
import ru.kpfu.itis.kirillakhmetov.entity.Profitability;

import java.util.List;

public class AnalyticsService {
    private final AnalyticsDao analyticsDao;

    public AnalyticsService(AnalyticsDao analyticsDao) {
        this.analyticsDao = analyticsDao;
    }

    public String getBusinessProfitability() {
        List<ProfitabilityRecordDto> profitabilities =
                analyticsDao.getAll().stream()
                        .map(profitability -> new ProfitabilityRecordDto(
                                Integer.toString(profitability.getDate().getYear()),
                                profitability.getValue().intValue()))
                        .toList();
        String jsonObject;
        try {
            jsonObject = new ObjectMapper().writeValueAsString(profitabilities);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка перевода данных в json");
        }
        return jsonObject;
    }
}
