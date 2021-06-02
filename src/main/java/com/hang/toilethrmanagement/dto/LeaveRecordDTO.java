package com.hang.toilethrmanagement.dto;

import com.hang.toilethrmanagement.model.LeaveRecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LeaveRecordDTO {
    private Integer id;
    private Long leaveStartTime;
    private Long leaveEndTime;
    private String leaveReason;
    @ApiModelProperty("0:未审批，1:通过，2:不通过")
    private String pass;        //0:未审批，1:通过，2:不通过
    private String lastTime;

    private String staffName;
    private String approcverName;

    public LeaveRecordDTO() {
    }

    public LeaveRecordDTO(LeaveRecord leaveRecord, String staffName, String approcverName) {
        this.approcverName = approcverName;
        this.staffName = staffName;

        this.id = leaveRecord.getId();
        this.leaveStartTime = leaveRecord.getLeaveStartTime();
        this.leaveEndTime = leaveRecord.getLeaveEndTime();
        this.leaveReason = leaveRecord.getLeaveReason();
        this.pass = leaveRecord.getPass();
        this.lastTime = leaveRecord.getLastTime();
    }
}
