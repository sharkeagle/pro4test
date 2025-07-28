package com.project4test.project4test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project4test.project4test.entity.User;
import com.project4test.project4test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    @RequestMapping("/login")
    public String login(String loginId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_id", loginId);
        return userService.getOne(queryWrapper).toString();
    }
}
