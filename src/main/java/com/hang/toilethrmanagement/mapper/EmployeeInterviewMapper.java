package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.EmployeeInterview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeInterviewMapper {

    List<EmployeeInterview> getEmployeeInterviewList(String query, int pageNum, int pageSize);

    int countEmployeeInterview(String query);

    int addEmployeeInterview(EmployeeInterview employeeInterview);

    int updateEmployeeInterviewById(EmployeeInterview employeeInterview);

    EmployeeInterview getEmployeeInterviewById(Integer id);

    int pass(Integer id);
}
