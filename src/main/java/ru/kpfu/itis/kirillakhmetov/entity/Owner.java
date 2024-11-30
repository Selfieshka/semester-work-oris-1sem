package ru.kpfu.itis.kirillakhmetov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Owner {
    private Long owner_id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int age;
    private String email;
    private String password;
    private String businessName;
}
