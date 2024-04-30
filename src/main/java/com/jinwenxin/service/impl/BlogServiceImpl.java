package com.jinwenxin.service.impl;

import com.jinwenxin.entity.Blog;
import com.jinwenxin.mapper.BlogMapper;
import com.jinwenxin.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jinwenxin
 * @since 2024-03-21
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
