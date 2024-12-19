package ru.kpfu.itis.kirillakhmetov.dto;

import java.util.List;

public record ProfitDto(List<String> date, List<String> amount, String forecastDate, String forecastAmount) {
}
