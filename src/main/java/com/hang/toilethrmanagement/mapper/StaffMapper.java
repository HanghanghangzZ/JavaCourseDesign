package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.Staff;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StaffMapper {

    List<Staff> getStaffList(String query, int offset, int pageSize);

    int countStaff(String query);

    int updateStaffById(Staff staff);

    int insertStaff(Staff staff);

    String getStaffNameById(Integer staffId);
}
