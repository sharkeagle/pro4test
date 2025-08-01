package com.project4test.project4test.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.qo.UserLoginQo;
import com.project4test.project4test.qo.UserRegisterQo;
import com.project4test.project4test.service.UserService;
import com.project4test.project4test.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    @RequestMapping("/login")
    public Result<UserVo> login(UserLoginQo loginQo) {
        return userService.login(loginQo);
    }

    @SaCheckLogin
    @RequestMapping("/logout")
    public Result<String> logout() {
        return userService.logout();
    }

    @RequestMapping("/register")
    @SentinelResource(value = "register")
    public Result<String> register(UserRegisterQo userRegisterQo) {
        return userService.register(userRegisterQo);
    }

    @SaCheckLogin
    @RequestMapping("/unregister")
    public Result<String> unregister() {
        return userService.unregister();
    }


}
