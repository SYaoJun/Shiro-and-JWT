package com.alphaboy.controller;

import com.alphaboy.common.JWTUtils;
import com.alphaboy.pojo.User;
import com.alphaboy.service.UserService;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yaojun
 * @create 2021-01-24 14:27
 */
@RestController
@Slf4j
public class JwtController {
    @Autowired
    private UserService userService;
    /*浏览器输出：http://localhost:8080/user/login?name=java&pwd=777*/
    @GetMapping("/user/login")
    public Map<String, Object> login( User user){
        log.info("用户名：[{}]", user.getName());
        log.info("密码：[{}]", user.getPwd());

        Map<String, Object> map = new HashMap<>();
        try{
            User userDB = userService.findByName(user.getName());
            /*生成JWT令牌*/
            Map<String, String> payload = new HashMap<>();
            payload.put("id", String.valueOf(userDB.getId()));
            payload.put("name", userDB.getName());
            payload.put("pwd", String.valueOf(userDB.getPwd()));
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token", token);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("state",false);
        map.put("msg","认证失败");
        return map;
    }

    @GetMapping("/user/test")
    public Map<String, Object> test(String token){
        Map<String, Object> map = new HashMap<>();
        if(token ==null){
            map.put("state",false);
            map.put("msg","token为空");
            return map;
        }
        log.info("当前的token为：[{}]", token);
        try {
            DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
            map.put("state",true);
            map.put("msg","请求成功");
            return map;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg","签名错误");
        }catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg","令牌过期");
        }catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg","加解密算法不匹配");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("msg","token无效");
        }
        map.put("state",false);
        return map;
    }

}
