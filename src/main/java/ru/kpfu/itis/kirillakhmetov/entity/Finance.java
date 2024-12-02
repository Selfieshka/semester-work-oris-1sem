package ru.kpfu.itis.kirillakhmetov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Finance {
    private Long id;
    private Long financeTypeId;
    private double value;
    private LocalDate date;
}
