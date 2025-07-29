package com.project4test.project4test.service.serviceimpl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project4test.project4test.dao.SysRoleDao;
import com.project4test.project4test.dao.SysUserRoleDao;
import com.project4test.project4test.entity.SysRole;
import com.project4test.project4test.entity.SysUserRole;
import com.project4test.project4test.qo.UserLoginQo;
import com.project4test.project4test.dao.UserDao;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.entity.User;
import com.project4test.project4test.enums.ResultCode;
import com.project4test.project4test.service.UserService;
import com.project4test.project4test.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    private final SysRoleDao sysRoleDao;
    private final SysUserRoleDao sysUserRoleDao;
    @Override
    public Result<UserVo> login(UserLoginQo loginQo) {
        log.info("loginQo:id{},pwd:{}", loginQo.getLoginId(),loginQo.getPwd());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_id", loginQo.getLoginId());
        User user= getOne(queryWrapper);
        if(user==null){
            return Result.fail(ResultCode.NOT_FOUND);
        }
        if(user.getPwd().equals(loginQo.getPwd())){
            StpUtil.login(user.getLoginId());
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            userVo.setToken(StpUtil.getTokenValue());
            return Result.success(userVo);
        }
        return Result.fail(ResultCode.PARAM_VALID_ERROR);
    }

    @Override
    public List<String> getPermRoleList(String loginId) {
        // 检查登录ID是否为空，如果为空则直接返回空列表
        if(StrUtil.isEmpty(loginId)){
            return Collections.emptyList();
        }
        // 创建用户查询条件包装器，根据登录ID查询用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_id", loginId);
        // 根据查询条件获取用户信息
        User user = getOne(queryWrapper);
        // 如果用户信息为空，则直接返回空列表
        if(user==null){
            return Collections.emptyList();
        }
        // 创建用户角色关联查询条件包装器，根据用户ID查询用户角色关联信息
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("user_id", user.getId());
        // 根据查询条件获取用户角色关联信息列表
        List<SysUserRole> sysUserRoles = sysUserRoleDao.selectList(sysUserRoleQueryWrapper);
        // 如果用户角色关联信息列表为空，则直接返回空列表
        if(sysUserRoles.isEmpty()){
            return Collections.emptyList();
        }
        // 用于存储用户角色名称的列表
        List<String> roles = new ArrayList<>();
        // 遍历用户角色关联信息列表
        for(SysUserRole sysUserRole : sysUserRoles){
            // 创建角色查询条件包装器，根据角色ID查询角色信息
            QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
            sysRoleQueryWrapper.eq("id", sysUserRole.getRoleId());
            // 根据查询条件获取角色信息
            SysRole sysRoles = sysRoleDao.selectOne(sysRoleQueryWrapper);
            // 如果角色信息为空，则跳过本次循环
            if(sysRoles==null){
                continue;
            }
            // 将角色名称添加到角色列表中
            roles.add(sysRoles.getRoleName());
        }

        return roles;
    }


}
