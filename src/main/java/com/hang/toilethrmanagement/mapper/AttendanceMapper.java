package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.Attendance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendanceMapper {


    List<Attendance> getAttendanceList(String query, int offset, int pageSize);

    int countAttendance(String query);

    int addAttendance(Attendance attendance);

    int updateAttendanceById(Attendance attendance);
}
