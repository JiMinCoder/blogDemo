package com.my.blog.dao;

import com.my.blog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @auther 周经明
 * @date 2020/3/18 17:11
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);


    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}

