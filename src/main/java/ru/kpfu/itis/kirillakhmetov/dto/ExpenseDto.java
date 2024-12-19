package ru.kpfu.itis.kirillakhmetov.dto;

import java.util.List;

public record ExpenseDto(List<String> categories, List<String> amounts) {
}
