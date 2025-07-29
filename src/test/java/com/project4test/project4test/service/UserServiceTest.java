package com.project4test.project4test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project4test.project4test.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    void getOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_id", "123");
        User user = userService.getOne(queryWrapper);
        assertEquals("朱佳", user.getName());
    }
    @Test
    void save() {
        User user = new User();
        user.setLoginId("122");
        user.setPwd("123");
        user.setName("朱佳");
        userService.save(user);
    }
    @Test
    void page() {

        IPage<User>pagfe1=userService.page(new Page<User>(2, 1), null);
        log.info(pagfe1.toString());
    }
    @Test
    void getPermRoleList() {
        log.info(userService.getPermRoleList("1234").toString());
    }

}