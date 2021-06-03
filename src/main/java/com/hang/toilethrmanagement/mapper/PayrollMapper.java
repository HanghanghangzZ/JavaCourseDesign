package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.Payroll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayrollMapper {
    List<Payroll> getPayrollList(String query, Integer offset, Integer pageSize);

    int countPayroll();

    void addPayroll(Payroll payroll);
}
