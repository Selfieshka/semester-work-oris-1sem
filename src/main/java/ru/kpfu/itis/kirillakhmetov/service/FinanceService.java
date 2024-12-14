package ru.kpfu.itis.kirillakhmetov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.kirillakhmetov.dao.FinanceDao;
import ru.kpfu.itis.kirillakhmetov.dto.ApiFinanceDto;
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

    public void addRevenue(FinanceDto financeDto) {
        financeDao.save(Finance.builder()
                .owner_id(financeDto.owner_id())
                .type("Доход")
                .amount(financeDto.amount())
                .category(financeDto.category())
                .date(financeDto.date())
                .build()
        );
    }

    public void addExpense(FinanceDto financeDto) {
        financeDao.save(Finance.builder()
                .owner_id(financeDto.owner_id())
                .type("Расход")
                .amount(financeDto.amount())
                .category(financeDto.category())
                .date(financeDto.date())
                .build()
        );
    }

    public String calculateRevenue(Long id) {
        double sumRevenue = financeDao.sumAllRevenue(id);
        String jsonObject;
        try {
            jsonObject = new ObjectMapper().writeValueAsString(new ApiFinanceDto(sumRevenue));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка перевода данных в json");
        }
        return jsonObject;
    }

    public String calculateExpense(Long id) {
        double sumExpense = financeDao.sumAllExpense(id);
        String jsonObject;
        try {
            jsonObject = new ObjectMapper().writeValueAsString(new ApiFinanceDto(sumExpense));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка перевода данных в json");
        }
        return jsonObject;
    }

    public String calculateProfit(Long idOwner) {
        double profit = financeDao.sumAllRevenue(idOwner) - financeDao.sumAllExpense(idOwner);
        String jsonObject;
        try {
            jsonObject = new ObjectMapper().writeValueAsString(new ApiFinanceDto(profit));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка перевода данных в json");
        }
        return jsonObject;
    }
}
