package com.alphaboy.pojo;

import com.alphaboy.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yaojun
 * @create 2020-12-12 10:23
 */
public class UserRealm extends AuthorizingRealm {
    /*执行授权逻辑*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }
    /*执行认证逻辑*/
    @Autowired
    private UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*假设数据库中获取的用户名和密码*/
//        String name = "alpha";
//        String password = "123456";
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userService.findByName(token.getUsername());
        if(user == null){
            //用户名不存在
            return null; //shiro底层会抛出UnknowAccountException
        }
        System.out.println("执行认证逻辑");
        /*判断密码*/

        System.out.println("密码是："+user.getPwd());
        return new SimpleAuthenticationInfo("",String.valueOf(user.getPwd()),"");
    }
}
