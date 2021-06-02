package com.hang.toilethrmanagement.controller;

import com.hang.toilethrmanagement.model.Token;
import com.hang.toilethrmanagement.model.User;
import com.hang.toilethrmanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录模块")
@RestController
@CrossOrigin
public class AuthorizeController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("登录")
    @GetMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        System.out.println(user);

        Token token = userService.login(user);

        if (token != null) {
            result.put("status", 200);
            result.put("msg", "登录成功");
            result.put("token", token.getToken());
            result.put("userId", token.getUserId());
        } else {
            result.put("status", 500);
            result.put("msg", "登录失败");
        }

        return result;
    }

    @ApiOperation("退出登录")
    @DeleteMapping("/logout/{userId}")
    public Map<String, Object> logout(@PathVariable Integer userId) {
        HashMap<String, Object> result = new HashMap<>();

        int i = userService.logout(userId);

        if (i > 0) {
            result.put("status", 200);
            result.put("msg", "下线成功");
        } else {
            result.put("status", 500);
            result.put("msg", "下线失败");
        }

        return result;
    }


}
