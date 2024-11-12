package ru.kpfu.itis.kirillakhmetov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Employee {
    private Integer employee_id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String effectiveDate;
    private String position;
    private Integer salary;
}
