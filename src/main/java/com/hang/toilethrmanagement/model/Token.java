package com.hang.toilethrmanagement.model;

import lombok.Data;

@Data
public class Token {
    private Integer id;
    private Integer userId;
    private Long expireTime;
    private String token;
}
