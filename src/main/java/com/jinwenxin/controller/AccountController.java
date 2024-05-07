package com.jinwenxin.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinwenxin.common.dto.LoginDto;
import com.jinwenxin.common.lang.Result;
import com.jinwenxin.entity.User;
import com.jinwenxin.service.UserService;
import com.jinwenxin.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在"); // assert 会抛出异常，没有response，而result.Fail不算异常

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误");
        }
        //密码正确，生成jwt
        String jwt = jwtUtils.generateToken(user.getId());
        // jwt 返回在header中还是在返回数据中？一般放在header中，延期的时候直接刷新

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        return Result.succ(MapUtil.builder().put("id", user.getId()).put("username", user.getUsername()).put("avatar",user.getAvatar()).put("email",user.getEmail()).map());

    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
