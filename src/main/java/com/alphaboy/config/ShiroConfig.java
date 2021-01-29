package com.alphaboy.config;

import com.alphaboy.pojo.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yaojun
 * @create 2020-12-12 10:21
 */
//@Configuration
public class ShiroConfig {
/*创建ShiroFilterFactoryBean*/
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        /*
        * anon:无需认证（登录）可以访问
        * authc:必须认证才可以访问
        * user:如何使用rememberMe的功能可以直接访问
        * perms:获取资源权限
        * role:获取角色权限
        * */
        Map<String, String > map = new LinkedHashMap<>();
//        map.put("/add", "authc");
//        map.put("/update", "authc");
        /*放行部分页面*/
        map.put("/thymeleaf", "anon");
        map.put("/login", "anon");
        /*通配符过滤,过滤的是url*/
        map.put("/*", "authc");

        //修改跳转的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

/*创建DefaultWebSecurityManager*/
    @Bean("securityManager")
    public DefaultSecurityManager getDefaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultSecurityManager SecurityManager = new DefaultWebSecurityManager();
        //设置realm
        SecurityManager.setRealm(userRealm);
        return SecurityManager;
    }


/*创建Realm*/
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
