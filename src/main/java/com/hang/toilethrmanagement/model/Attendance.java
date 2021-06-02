package com.hang.toilethrmanagement.model;

import lombok.Data;

@Data
public class Attendance {
    private Integer id;
    private Integer staffId;
    private Long arriveTime;
    private Long leaveTime;
    private String lastTime;
}
