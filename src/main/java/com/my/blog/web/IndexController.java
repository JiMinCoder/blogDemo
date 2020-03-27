package com.my.blog.web;

import com.my.blog.service.BlogService;
import com.my.blog.service.TagService;
import com.my.blog.service.TypeService;
import com.my.blog.vo.BlogQuery;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.GeneratedValue;

/**
 * 主页
 * @auther 周经明
 * @date 2020/3/16 10:03
 */
@Controller
public class IndexController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                BlogQuery blog, Model model){
            model.addAttribute("page",blogService.listBlog(pageable));
            model.addAttribute("types",typeService.listTypeTop(6));
            model.addAttribute("tags",tagService.listTagTop(10));
            model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
            footerBlog(model);
            return "index";
    }

    @GetMapping("/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        footerBlog(model);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        footerBlog(model);
        return "blog";
    }

    @GetMapping("/footerBlog")
    public String footerBlog(Model model){
        model.addAttribute("footerBlog",blogService.listRecommendBlogTop(3));
        return "_fragments";
    }

}
