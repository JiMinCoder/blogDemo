package com.my.blog.service.impl;

import com.my.blog.dao.UserRepository;
import com.my.blog.po.User;
import com.my.blog.service.UserService;
import com.my.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther 周经明
 * @date 2020/3/17 17:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User checkUser(String username, String password) {
        User user = repository.findByUsernameAndPassword(username,MD5Utils.code(password));

        return user;
    }


}
