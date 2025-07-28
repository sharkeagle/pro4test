package com.project4test.project4test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project4test.project4test.Qo.UserLoginQo;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.entity.User;
import com.project4test.project4test.vo.UserVo;

public interface UserService extends IService<User> {
    Result<UserVo> login(UserLoginQo userLoginQo);
}
