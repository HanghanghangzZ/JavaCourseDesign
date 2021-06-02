package com.hang.toilethrmanagement.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Position {
    private Integer id;
    private String name;
    private BigDecimal basicWage;
    private Integer count;
}
