package com.jinwenxin.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinwenxin.common.lang.Result;
import com.jinwenxin.entity.Blog;
import com.jinwenxin.service.BlogService;
import com.jinwenxin.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jinwenxin
 * @since 2024-03-20
 */
//@Controller
    @RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage<Blog> result = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(result);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除");
        return Result.succ(blog);
    }
    @PostMapping("/blog/edit")
    @RequiresAuthentication
    public Result edit(@Validated @RequestBody Blog blog){
        // 新增/编辑 判断是否有id
        if(blog.getId()!=null){
            Blog oldBlog = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            System.out.println("该文章的作者id是"+oldBlog.getUserId());
            Assert.isTrue(oldBlog.getUserId().equals(ShiroUtil.getProfile().getId()),"没有权限编辑");
            BeanUtils.copyProperties(blog,oldBlog,"id","userId","created","status");
            blogService.saveOrUpdate(oldBlog);

        }else{
            // 添加新博客
            Blog addBlog = new Blog();
            addBlog.setUserId(ShiroUtil.getProfile().getId());
            addBlog.setCreated(LocalDateTime.now());
            addBlog.setStatus(0);
            BeanUtils.copyProperties(blog,addBlog,"id","userId","created","status");
            blogService.saveOrUpdate(addBlog);

        }
        return Result.succ(null);
    }
}
