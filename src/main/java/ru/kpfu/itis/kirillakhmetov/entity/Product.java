package ru.kpfu.itis.kirillakhmetov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {
    private String name;
    private String unitMeasure;
    private Double quantity;
    private Double costPerUnit;
}
