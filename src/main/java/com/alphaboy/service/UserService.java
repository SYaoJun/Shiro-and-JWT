package com.alphaboy.service;

import com.alphaboy.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author yaojun
 * @create 2020-12-12 17:36
 */
@Service
public interface UserService {
    User findByName(String name);
}
