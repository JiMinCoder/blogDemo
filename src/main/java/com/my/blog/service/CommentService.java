package com.my.blog.service;

import com.my.blog.po.Comment;

import java.util.List;

/**
 * @auther 周经明
 * @date 2020/3/21 14:47
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long id);

    Comment saveComment(Comment comment);

}
