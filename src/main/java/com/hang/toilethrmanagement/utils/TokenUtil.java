package com.hang.toilethrmanagement.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hang.toilethrmanagement.model.Token;
import com.hang.toilethrmanagement.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    //设置一天过期
    private static final long EXPIRE_DATE = 1000 * 60 * 60 * 24;
    private static final String TOKEN_SECRET = "6URqn1geA8lKo0zX5Ca0WpxdHNg2I8";

    public static Token tokenTest(User user) {
        Token token = new Token();

        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
        //秘钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //携带username，password信息，生成签名
        token.setToken(JWT.create()
                .withHeader(header)
                .withClaim("username", user.getUsername())
                .withClaim("password", user.getPassword())
                .withExpiresAt(date)
                .sign(algorithm));
        token.setExpireTime(date.getTime());
        token.setUserId(user.getId());

        return token;
    }
}
