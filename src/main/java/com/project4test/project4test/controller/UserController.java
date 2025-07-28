package com.project4test.project4test.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project4test.project4test.Qo.UserLoginQo;
import com.project4test.project4test.Qo.UserRegisterQo;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.entity.User;
import com.project4test.project4test.enums.ResultCode;
import com.project4test.project4test.service.UserService;
import com.project4test.project4test.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
        log.info("loginQo:id{},pwd:{}", loginQo.getLoginId(),loginQo.getPwd());
        return userService.login(loginQo);
    }
    // 登录校验：只有登录之后才能进入该方法
    @SaCheckLogin
    @RequestMapping("/logout")
    public String logout(String loginId) {
        StpUtil.logout(loginId);
        return "注销成功";
    }

    @RequestMapping("/register")
    public UserVo register(UserRegisterQo userRegisterQo) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterQo, user);
        userService.save(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }


}
