package com.hang.toilethrmanagement.dto;

import com.hang.toilethrmanagement.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;

    private String staffName;
    private Integer staffId;

    public UserDTO(User user, String staffName) {
        this.staffName = staffName;

        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.staffId = user.getStaffId();
    }
}