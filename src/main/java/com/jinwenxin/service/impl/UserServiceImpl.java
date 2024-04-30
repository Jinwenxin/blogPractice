package com.jinwenxin.service.impl;

import com.jinwenxin.entity.User;
import com.jinwenxin.mapper.UserMapper;
import com.jinwenxin.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
