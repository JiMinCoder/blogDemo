package com.my.blog.service.impl;

import com.my.blog.po.User;
import com.my.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther 周经明
 * @date 2020/3/18 9:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    void checkUser() {
        String username = "m";
        String password = "123";
        User user = service.checkUser(username,password);
        if (user==null){
            System.out.println("错误");
        }else {
            System.out.println("正确");
        }
    }
}