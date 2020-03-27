package com.my.blog.service.impl;

import com.my.blog.dao.BlogRepository;
import com.my.blog.po.Blog;
import com.my.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther 周经明
 * @date 2020/3/19 16:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class BlogServiceImplTest {

    @Autowired
    private BlogService service;

    @Autowired
    private BlogRepository repository;

}