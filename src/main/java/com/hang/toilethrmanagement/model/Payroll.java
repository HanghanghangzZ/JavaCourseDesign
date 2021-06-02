package com.hang.toilethrmanagement.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payroll {
    private Integer id;
    private Integer staffId;
    private BigDecimal basicWage;
    private BigDecimal bonus;
    private Long date;
    private BigDecimal total;
}
