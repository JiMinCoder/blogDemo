package com.my.blog.service.impl;

import com.my.blog.NotFoundException;
import com.my.blog.dao.TagRepository;
import com.my.blog.po.Tag;
import com.my.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther 周经明
 * @date 2020/3/18 17:13
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository repository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return repository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return repository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return repository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return repository.findTop(pageable);
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = repository.getOne(id);
        if (t==null){
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag,t);
        return repository.save(t);
    }


    @Transactional
    @Override
    public void deleteTag(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return repository.findByName(name);
    }
}
