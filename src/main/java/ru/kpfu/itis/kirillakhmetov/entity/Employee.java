package ru.kpfu.itis.kirillakhmetov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String effectiveDate;
}
