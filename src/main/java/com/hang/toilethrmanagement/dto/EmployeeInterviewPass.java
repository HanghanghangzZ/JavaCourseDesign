package com.hang.toilethrmanagement.dto;

import com.hang.toilethrmanagement.model.Staff;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmployeeInterviewPass {
    @ApiModelProperty("面试通过人员的id")
    private Integer id;
    private String name;
    private String idCard;
    private Integer positionId;
    private Integer age;
    private String sex;
    private Integer qualificationId;
    private String education;
    private Long contractExpirationTime;

    public static Staff passToStaff(EmployeeInterviewPass employeeInterviewPass) {
        Staff staff = new Staff();
        staff.setName(employeeInterviewPass.getName());
        staff.setIdCard(employeeInterviewPass.getIdCard());
        staff.setPositionId(employeeInterviewPass.getPositionId());
        staff.setAge(employeeInterviewPass.getAge());
        staff.setSex(employeeInterviewPass.getSex());
        staff.setQualificationId(employeeInterviewPass.getQualificationId());
        staff.setEducation(employeeInterviewPass.getEducation());
        staff.setContractExpirationTime(employeeInterviewPass.getContractExpirationTime());

        staff.setEntryTime(System.currentTimeMillis());

        return staff;
    }
}
