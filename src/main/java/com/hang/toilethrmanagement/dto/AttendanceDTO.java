package com.hang.toilethrmanagement.dto;

import com.hang.toilethrmanagement.model.Attendance;
import lombok.Data;

@Data
public class AttendanceDTO {
    private Integer id;
    private String staffName;
    private Long arriveTime;
    private Long leaveTime;
    private String lastTime;

    public AttendanceDTO() {
    }

    public AttendanceDTO(Attendance attendance, String staffName) {
        this.staffName = staffName;

        this.id = attendance.getId();
        this.arriveTime = attendance.getArriveTime();
        this.leaveTime = attendance.getLeaveTime();
        this.lastTime = attendance.getLastTime();
    }
}
