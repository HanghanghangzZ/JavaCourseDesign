package com.hang.toilethrmanagement.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Training {
    private Integer id;
    private String trainingMethod;
    private BigDecimal score;
    private Integer staffId;
    private Long startTime;
    private Long endTime;
    private String lastTime;
}
