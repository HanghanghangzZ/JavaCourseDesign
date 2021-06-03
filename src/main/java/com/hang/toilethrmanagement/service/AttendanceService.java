package com.hang.toilethrmanagement.service;

import com.hang.toilethrmanagement.dto.AttendanceDTO;
import com.hang.toilethrmanagement.mapper.AttendanceMapper;
import com.hang.toilethrmanagement.mapper.StaffMapper;
import com.hang.toilethrmanagement.model.Attendance;
import com.hang.toilethrmanagement.utils.TimestampDiff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private AttendanceMapper attendanceMapper;

    @Autowired
    public void setAttendanceMapper(AttendanceMapper attendanceMapper) {
        this.attendanceMapper = attendanceMapper;
    }

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Transactional
    public List<AttendanceDTO> getAttendanceDTOList(String query, int offset, int pageSize) {
        List<Attendance> attendanceList = attendanceMapper.getAttendanceList(query, offset, pageSize);

        List<AttendanceDTO> attendanceDTOList = attendanceList.stream().map(attendance -> {

            String staffNameById = staffMapper.getStaffNameById(attendance.getStaffId());
            AttendanceDTO attendanceDTO = new AttendanceDTO(attendance, staffNameById);

            return attendanceDTO;
        }).collect(Collectors.toList());

        return attendanceDTOList;
    }

    public int countAttendance(String query) {

        return attendanceMapper.countAttendance(query);
    }

    public int addAttendance(Attendance attendance) {
        String lastTime = TimestampDiff.diffToTimeStamp(attendance.getLeaveTime() - attendance.getArriveTime());
        attendance.setLastTime(lastTime);

        return attendanceMapper.addAttendance(attendance);
    }

    public int updateAttendanceById(Attendance attendance) {
        String lastTime = TimestampDiff.diffToTimeStamp(attendance.getLeaveTime() - attendance.getArriveTime());
        attendance.setLastTime(lastTime);

        return attendanceMapper.updateAttendanceById(attendance);
    }
}
