package ru.kpfu.itis.kirillakhmetov.dto;

import java.time.LocalDate;

public record EmployeeDto(String firstName, String lastName, String patronymic, LocalDate effectiveDate,
                          String position, Integer salary) {
}
