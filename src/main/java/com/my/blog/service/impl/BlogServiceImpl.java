package com.my.blog.service.impl;

import com.my.blog.NotFoundException;
import com.my.blog.dao.BlogRepository;
import com.my.blog.po.Blog;
import com.my.blog.po.Type;
import com.my.blog.service.BlogService;
import com.my.blog.util.MarkdownUtils;
import com.my.blog.util.MyBeanUtils;
import com.my.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @auther 周经明
 * @date 2020/3/19 8:25
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository repository;

    @Override
    public Blog getBlog(Long id) {
        repository.updateViews(id);
        return repository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return repository.findAll(new Specification<Blog>() {
            @Override
//            root 是 blog 对象 criteriaQuery是参数容器
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if (!"".equals(blog.getTypeId()) && blog.getTypeId() != null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if (blog.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Blog> listBlogTop(Integer size) {
//        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size);
        return repository.findByTop(pageable);
    }


    @Override
    public Page<Blog> listBlog(Long tagId,Pageable pageable) {
        return repository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return repository.findByQuery(query,pageable);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = repository.getOne(id);
//        更新观看次数。
        repository.updateViews(id);
        if(blog == null){
            throw new NotFoundException("博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent( MarkdownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return repository.findTop(pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
//        判断是修改还是添加
        if (blog.getId() == null){
            blog.setFlag("原创");
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }
        blog.setUpdateTime(new Date());
        return repository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b  = repository.getOne(id);
        if (b==null){
            throw new NotFoundException("博客不存在");
        }
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return repository.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = repository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, repository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return repository.count();
    }
}
