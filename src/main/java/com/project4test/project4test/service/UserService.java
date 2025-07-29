package com.project4test.project4test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project4test.project4test.qo.UserLoginQo;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.entity.User;
import com.project4test.project4test.vo.UserVo;

import java.util.List;

public interface UserService extends IService<User> {
    /**
     * 登录
     *
     * @param userLoginQo 用户的登录信息
     * @return 登录成功返回用户信息，登录失败返回null
     */
    Result<UserVo> login(UserLoginQo userLoginQo);
    /**
     * 根据用户登录ID获取用户的角色权限列表
     *
     * @param loginId 用户的登录ID
     * @return 包含用户角色名称的列表，如果没有则返回空列表
     */
    List<String> getPermRoleList(String loginId);
}
