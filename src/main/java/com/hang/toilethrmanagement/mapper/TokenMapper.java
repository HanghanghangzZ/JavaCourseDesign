package com.hang.toilethrmanagement.mapper;

import com.hang.toilethrmanagement.model.Token;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    int generateToken(Token token);

    int deleteToken(Integer userId);

    int tokenExistAndNotExpire(String token, Integer userId, long currentTimeMillis);

    int containUserId(Integer id);

    int updateToken(Token token);
}
