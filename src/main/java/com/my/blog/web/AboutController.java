package com.my.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther 周经明
 * @date 2020/3/22 11:46
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
