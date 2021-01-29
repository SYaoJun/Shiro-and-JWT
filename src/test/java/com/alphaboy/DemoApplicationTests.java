package com.alphaboy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;


class DemoApplicationTests {

    @Test
    void getToken() {
        HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 100);
        String token = JWT.create()
                .withHeader(map)  //header 使用默认值
                .withClaim("id", 123)  // payload
                .withClaim("name", "yaojun")
                .withExpiresAt(instance.getTime()) //过期时间
                .sign(Algorithm.HMAC256("asdfasdfasdf"));//signature
        System.out.println(token);
    }
    @Test
    void verifyToken(){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("asdfasdfasdf")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoieWFvanVuIiwiaWQiOjEyMywiZXhwIjoxNjExNDcxMzE3fQ.pFUfg2ORPwiF__JReeJrg8hzNTuUD3joiPkTxj5p1NA");
        System.out.println(decodedJWT.getClaim("id").asInt());
        System.out.println(decodedJWT.getClaim("name").asString());
    }

}
