package com.jinwenxin.controller;

import com.jinwenxin.common.lang.Result;
import com.jinwenxin.entity.User;
import com.jinwenxin.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jinwenxin
 * @since 2024-03-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/index")
    @RequiresAuthentication
    public Result index(){
        User user = userService.getById(1L);
        return Result.succ(200,"success",user);
    }

    @PostMapping("/save")
    //@RequiresAuthentication
    //@Validated 规则不满足抛出异常
    public Result save(@Valid @RequestBody User user){
        //userService.save(user);
        return Result.succ(user);
    }
}
