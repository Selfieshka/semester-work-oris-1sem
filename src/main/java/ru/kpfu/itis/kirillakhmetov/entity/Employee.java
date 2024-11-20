package ru.kpfu.itis.kirillakhmetov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Employee {
    private Integer employee_id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate effectiveDate;
    private String position;
    private Integer salary;
}
