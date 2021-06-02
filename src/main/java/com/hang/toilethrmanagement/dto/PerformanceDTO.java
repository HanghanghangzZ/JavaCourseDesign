package com.hang.toilethrmanagement.dto;

import com.hang.toilethrmanagement.model.Performance;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PerformanceDTO {
    private Integer id;
    private Long date;
    private BigDecimal bonus;

    private String staffName;

    public PerformanceDTO() {
    }

    public PerformanceDTO(Performance performance, String staffName) {
        this.staffName = staffName;

        this.id = performance.getId();
        this.date = performance.getDate();
        this.bonus = performance.getBonus();
    }
}
