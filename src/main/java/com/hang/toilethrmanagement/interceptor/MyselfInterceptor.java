package com.hang.toilethrmanagement.interceptor;

import com.hang.toilethrmanagement.mapper.TokenMapper;
import com.hang.toilethrmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyselfInterceptor implements HandlerInterceptor {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private TokenMapper tokenMapper;

    @Autowired
    public void setTokenMapper(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getRequestURI().contains("login")) {
            return true;
        }

        String token = request.getHeader("token");
        Integer userId = Integer.valueOf(request.getHeader("userId"));

        int i = tokenMapper.tokenExistAndNotExpire(token, userId, System.currentTimeMillis());

        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

}
