package com.my.blog.service.impl;

import com.my.blog.po.Tag;
import com.my.blog.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther 周经明
 * @date 2020/3/27 15:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class TagServiceImplTest {

    @Autowired
    private TagService service;

    @Test
    void listTagTop() {
        List<Tag> tags = service.listTagTop(3);
        System.out.println(tags);
    }
}