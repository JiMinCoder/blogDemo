package com.my.blog.service;

import com.my.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.lang.model.element.TypeElement;
import java.util.List;

/**
 * @auther 周经明
 * @date 2020/3/18 13:30
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id, Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);
}
