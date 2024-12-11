package ru.kpfu.itis.kirillakhmetov.dto;

import java.time.LocalDate;

public record InvoiceDto(Long ownerId, String number, LocalDate date) {
}
