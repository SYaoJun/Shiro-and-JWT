package com.alphaboy.service;


import com.alphaboy.mapper.UserMapper;
import com.alphaboy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yaojun
 * @create 2020-12-12 17:00
 */
@Service
public class UserServiceImpl implements UserService{
    // 注入mapper接口
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}
