package com.my.blog.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther 周经明
 * @date 2020/3/23 8:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class BlogRepositoryTest {

    @Autowired
    private BlogRepository repository;

    @Test
    void updateViews() {

        repository.updateViews((long) 23);

    }
}