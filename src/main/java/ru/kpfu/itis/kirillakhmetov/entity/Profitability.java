package ru.kpfu.itis.kirillakhmetov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Profitability {
    private Long profitability_id;
    private Double value;
    private LocalDate date;

    public Profitability(Double value, LocalDate date) {
        this.value = value;
        this.date = date;
    }
}
