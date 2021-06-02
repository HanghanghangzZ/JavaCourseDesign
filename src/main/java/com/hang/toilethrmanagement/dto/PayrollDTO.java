package com.hang.toilethrmanagement.dto;

import com.hang.toilethrmanagement.model.Payroll;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayrollDTO {
    private Integer id;
    private BigDecimal basicWage;
    private BigDecimal bonus;
    private Long date;
    private BigDecimal total;

    private String staffName;

    public PayrollDTO() {
    }

    public PayrollDTO(Payroll payroll, String staffName) {
        this.staffName = staffName;

        this.id = payroll.getId();
        this.basicWage = payroll.getBasicWage();
        this.bonus = payroll.getBonus();
        this.date = payroll.getDate();
        this.total = payroll.getTotal();
    }
}
