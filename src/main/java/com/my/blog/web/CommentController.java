package com.my.blog.web;

import com.my.blog.po.Comment;
import com.my.blog.po.User;
import com.my.blog.service.BlogService;
import com.my.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @auther 周经明
 * @date 2020/3/21 14:43
 */
@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

//    @Value("${comment.avatar:#{null}}")
    private String avatar = "http://pic2.zhimg.com/50/v2-fb824dbb6578831f7b5d92accdae753a_hd.jpg";

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvater());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }


}
