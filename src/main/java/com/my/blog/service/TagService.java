package com.my.blog.service;

import com.my.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @auther 周经明
 * @date 2020/3/18 17:12
 */
public interface TagService {

    Tag saveTag(Tag Tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    Tag updateTag(Long id, Tag Tag);

    void deleteTag(Long id);

    Tag getTagByName(String name);
}
