package com.alphaboy.mapper;

import com.alphaboy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yaojun
 * @create 2020-12-08 17:27
 * mapper通常作为持久层，也就是mybatis的核心功能
 */

@Repository
@Mapper
//表示这是一个mybatis的mapper类
public interface UserMapper {
    List<User> queryUserList();
    User queryUserById(int id);
    User findByName(String name);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
}
