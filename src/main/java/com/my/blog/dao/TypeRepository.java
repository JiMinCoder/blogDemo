package com.my.blog.dao;

import com.my.blog.intercoptor.LoginIntercopter;
import com.my.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @auther 周经明
 * @date 2020/3/18 13:33
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
