package com.my.blog.dao;

import com.my.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @auther 周经明
 * @date 2020/3/17 17:31
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);

}
