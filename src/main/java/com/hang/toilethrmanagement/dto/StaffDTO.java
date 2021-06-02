package com.hang.toilethrmanagement.dto;

import com.hang.toilethrmanagement.model.Staff;
import lombok.Data;

@Data
public class StaffDTO {
    private Integer id;
    private String name;
    private String idCard;
    private Integer age;
    private String sex;
    private String education;
    private Long entryTime;
    private Long contractExpirationTime;
    private Boolean isResigned;     //true:已辞职，false:未辞职

    private String positionName;
    private String qualificationName;

    public StaffDTO() {
    }

    public StaffDTO(Staff staff, String positionName, String qualificationName) {
        this.positionName = positionName;
        this.qualificationName = qualificationName;

        this.id = staff.getId();
        this.name = staff.getName();
        this.idCard = staff.getIdCard();
        this.age = staff.getAge();
        this.sex = staff.getSex();
        this.education = staff.getEducation();
        this.entryTime = staff.getEntryTime();
        this.contractExpirationTime = staff.getContractExpirationTime();
        this.isResigned = staff.getIsResigned();
    }
}
