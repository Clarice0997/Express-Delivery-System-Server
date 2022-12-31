package com.example.expressdeliverysystemserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.expressdeliverysystemserver.entity.Account;

import java.util.Calendar;

public class JWTUtils {
    // 7天过期
    private static long expire = 604800;
    // 32位秘钥
    private static String secret = "abcdfghiabcdfghiabcdfghiabcdfghi";

    /**
     * 生成token
     * @param account
     * @return token
     */
    public static String generateToken(Account account){
        Calendar instance = Calendar.getInstance();
        // 默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);
        // 构建JWT
        JWTCreator.Builder builder = JWT.create();
        // JWT payload
        builder.withClaim("username", account.getUsername())
                .withClaim("userId", account.getUid());
        // 返回JWT字符串
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 验证token合法性 成功返回用户对象
     * @param token
     * @return account
     */
    public static Account verify(String token) {
        // 构建JWTVerifier对象
        JWTVerifier build = JWT.require(Algorithm.HMAC256(secret)).build();
        // 解码JWT
        DecodedJWT decodedJWT = build.verify(token);
        // 结构Payload
        int id = decodedJWT.getClaim("userId").asInt();
        String username = decodedJWT.getClaim("username").asString();
        // 持久化用户对象
        Account account = new Account();
        account.setUid(id);
        account.setUsername(username);
        // 返回用户对象
        return account;
    }
}
