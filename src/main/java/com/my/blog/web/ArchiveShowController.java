package com.my.blog.web;

import com.my.blog.service.BlogService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther 周经明
 * @date 2020/3/22 11:55
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private IndexController indexController;

    @GetMapping("/archive")
    public String archive(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        indexController.footerBlog(model);
        return "archives";
    }
}
