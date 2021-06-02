package com.hang.toilethrmanagement.model;

import lombok.Data;

@Data
public class Staff {
    private Integer id;
    private String name;
    private String idCard;
    private Integer positionId;
    private Integer age;
    private String sex;
    private Integer qualificationId;
    private String education;
    private Long entryTime;
    private Long contractExpirationTime;
    private Boolean isResigned;     //true:已辞职，false:未辞职
}
