package com.my.blog.service.impl;

import com.my.blog.NotFoundException;
import com.my.blog.dao.TypeRepository;
import com.my.blog.po.Type;
import com.my.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther 周经明
 * @date 2020/3/18 13:32
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    /**
     * 保存（添加）
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type saveType(Type type) {

        return repository.save(type);
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Type getType(Long id) {
        return repository.getOne(id);
    }

    /**
     * 分页查询全部
     * @param pageable
     * @return
     */
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return repository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blog.size");
        Pageable pageable = PageRequest.of(0,size);
        return repository.findTop(pageable);
    }


    /**
     * 更新
     * @param id
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = repository.getOne(id);
        if (t==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return repository.save(t);
    }

    /**
     * 删除
     * @param id
     */
    @Transactional
    @Override
    public void deleteType(Long id) {
        repository.deleteById(id);
    }

    /**
     * 根据类型名称查询（用来查重）
     * @param name
     * @return
     */
    @Override
    public Type getTypeByName(String name) {
        return repository.findByName(name);
    }
}
