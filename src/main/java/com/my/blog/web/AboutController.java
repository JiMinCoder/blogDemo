package com.my.blog.web;

import org.jboss.jandex.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther 周经明
 * @date 2020/3/22 11:46
 */
@Controller
public class AboutController {

    @Autowired
    private IndexController indexController;

    @GetMapping("/about")
    public String about(Model model){
        indexController.footerBlog(model);
        return "about";
    }
}
