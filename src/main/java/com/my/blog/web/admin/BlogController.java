package com.my.blog.web.admin;

import com.my.blog.dao.BlogRepository;
import com.my.blog.po.Blog;
import com.my.blog.po.User;
import com.my.blog.service.BlogService;
import com.my.blog.service.TagService;
import com.my.blog.service.TypeService;
import com.my.blog.vo.BlogQuery;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @auther 周经明
 * @date 2020/3/18 11:21
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogRepository repository;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }


    // 条件查询 可以只更新 blogs.html里面的 blogList（需要在前端使用thymeleaf模板定义 th:fragment="blogList" ； 与jQuery load方法联合使用）
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 1,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }


//    跳转自添加界面

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

//    携带要修改的内容转自添加界面（修改）

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());

        System.out.println(repository.getOne(id));
        System.out.println(blogService.getBlog(id));

        Blog b = blogService.getBlog(id);

        b.init();

        model.addAttribute("blog",b);
        return INPUT;
    }


//    修改和添加共用提交
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() ==null){
            b = blogService.saveBlog(blog);
        }else {
            b = blogService.updateBlog(blog.getId(),blog);
        }

        if (b == null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return REDIRECT_LIST;
    }


    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("messgae","删除成功");
        return REDIRECT_LIST;
    }
}
