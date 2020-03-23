package com.my.blog.service;

import com.my.blog.po.User;

/**
 * @auther 周经明
 * @date 2020/3/17 17:28
 */
public interface UserService {

    User checkUser(String username,String password);
}
