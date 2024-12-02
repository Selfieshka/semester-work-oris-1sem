package ru.kpfu.itis.kirillakhmetov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate effectiveDate;
    private String position;
    private Integer salary;

    public Employee(String firstName, String lastName, String patronymic, LocalDate effectiveDate, String position, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.effectiveDate = effectiveDate;
        this.position = position;
        this.salary = salary;
    }
}
