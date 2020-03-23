package com.my.blog.dao;

import com.my.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @auther 周经明
 * @date 2020/3/21 14:49
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogId(Long blogId , Sort sort);
}
