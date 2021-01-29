package com.alphaboy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yaojun
 * @create 2020-12-08 17:26
 */
@Data
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private int pwd;

    public User(int id, String name, int pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }
}
