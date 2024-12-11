package ru.kpfu.itis.kirillakhmetov.entity;

import java.time.LocalDate;
import java.util.List;

public class Invoice {
    private Long id;
    private Long owner_id;
    private String number;
    private LocalDate date;
    private List<Product> quantity;
}
