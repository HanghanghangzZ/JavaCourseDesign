package com.hang.toilethrmanagement.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Qualification {
    private Integer id;
    private String name;
    private BigDecimal extraSalary;
    private Integer count;
}
