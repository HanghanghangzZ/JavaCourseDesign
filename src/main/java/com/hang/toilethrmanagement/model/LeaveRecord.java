package com.hang.toilethrmanagement.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LeaveRecord {
    private Integer id;
    private Long leaveStartTime;
    private Long leaveEndTime;
    private String leaveReason;
    private Integer staffId;
    @ApiModelProperty("0:未审批，1:通过，2:不通过")
    private String pass;        //0:未审批，1:通过，2:不通过
    @ApiModelProperty("审批人id")
    private Integer approcverId;
    private String lastTime;
}
