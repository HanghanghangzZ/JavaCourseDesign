package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.bean.Pagination;
import com.hang.toilethrmanagement.dto.UserDTO;
import com.hang.toilethrmanagement.enums.CRUDMsgEnum;
import com.hang.toilethrmanagement.model.User;
import com.hang.toilethrmanagement.service.UserService;
import com.hang.toilethrmanagement.utils.PaginationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户模块")
@RestController
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("获取用户数据")
    @GetMapping(value = {"/user/{query}/{pageNum}/{pageSize}", "/user/{pageNum}/{pageSize}"})
    public Map<String, Object> queryUser(@PathVariable(required = false) String query,
                                         @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        HashMap<String, Object> result = new HashMap<>();

        int totalCount = userService.countUser(query);

        Pagination pagination = PaginationUtil.getPagination(totalCount, pageSize, pageNum);
        pageNum = pagination.getPageNum();

        List<UserDTO> userDTOList = userService.getUserDTOList(query, pagination.getOffset(), pageSize);

        result.put("totalPage", pagination.getTotalPage());
        result.put("totalCount", totalCount);
        result.put("userDTOList", userDTOList);
        result.put("pageNum", pageNum);
        result.put("status", 200);
        result.put("msg", CRUDMsgEnum.SELECT_SUCCESS);

        return result;
    }

    @ApiOperation("添加用户")
    @PostMapping("/user")
    public Map<String, Object> addUser(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        int i = userService.addUser(user);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.INSERT_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.INSERT_FAIL);
        }
        return result;
    }

    @ApiOperation("更新用户")
    @PutMapping("/user")
    public Map<String, Object> updateUserById(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        int i = userService.updateUserById(user);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.UPDATE_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.UPDATE_FAIL);
        }

        return result;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/user/{id}")
    public Map<String, Object> deleteUserById(@PathVariable Integer id) {
        HashMap<String, Object> result = new HashMap<>();
        int i = userService.deleteUserById(id);
        if (i > 0) {
            result.put("status", 200);
            result.put("msg", CRUDMsgEnum.DELETE_SUCCESS);
        } else {
            result.put("status", 500);
            result.put("msg", CRUDMsgEnum.DELETE_FAIL);
        }
        return result;
    }
}