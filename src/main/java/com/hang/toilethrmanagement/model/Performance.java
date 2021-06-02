package com.hang.toilethrmanagement.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Performance {
    private Integer id;
    private Integer staffId;
    private Long date;
    private BigDecimal bonus;
}
