package com.project4test.project4test.service.serviceimpl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project4test.project4test.dao.SysRoleDao;
import com.project4test.project4test.dao.SysUserRoleDao;
import com.project4test.project4test.entity.SysRole;
import com.project4test.project4test.entity.SysUserRole;
import com.project4test.project4test.enums.UserCommonEnum;
import com.project4test.project4test.enums.UserLoginCode;
import com.project4test.project4test.qo.UserLoginQo;
import com.project4test.project4test.dao.UserDao;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.entity.User;
import com.project4test.project4test.enums.ResultCode;
import com.project4test.project4test.qo.UserRegisterQo;
import com.project4test.project4test.service.UserService;
import com.project4test.project4test.util.BcryptUtil;
import com.project4test.project4test.util.KacptchaUtil;
import com.project4test.project4test.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    private final SysRoleDao sysRoleDao;
    private final SysUserRoleDao sysUserRoleDao;
    private final KacptchaUtil kacptchaUtil;

    @Override
    public Result<String> register(UserRegisterQo userRegisterQo) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_phone",userRegisterQo.getLoginPhone());
        boolean userExist = this.exists(queryWrapper);
        if(userExist){
            return Result.fail(ResultCode.PHONE_BOUND);
        }

        User user = new User();
        BeanUtils.copyProperties(userRegisterQo, user);

        user.setPwd(BcryptUtil.hash(user.getPwd()));

        String uuid = UUID.randomUUID().toString();
        String headName = PinyinUtil.getPinyin(userRegisterQo.getName(),"");
        user.setLoginId(headName + "-" + uuid);
        boolean isSave = this.save(user);
        return isSave ? Result.success("注册成功") : Result.fail(ResultCode.FAILED);
    }

    @Override
    public Result<String> unregister() {
        String loginId = (String) StpUtil.getLoginId();
        log.info("loginId:{}",loginId);
        if(StrUtil.isEmpty(loginId)){
            return Result.fail(ResultCode.PARAM_VALID_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_id", loginId);
        User user = this.getOne(queryWrapper);
        if(user==null){
            return Result.fail(ResultCode.NOT_FOUND);
        }
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("login_id",loginId);
        updateWrapper.set("status",UserCommonEnum.STATUS_UNREGISTER.getCode());
        updateWrapper.set("used_phone",user.getLoginPhone());
        updateWrapper.set("login_phone",null);
        boolean isRemove= this.update(updateWrapper);
        this.logout();
        return isRemove?Result.success("注销成功"):Result.fail(ResultCode.FAILED);
    }

    @Override
    public Result<UserVo> login(UserLoginQo loginQo) {
        log.info("loginQo:id{},pwd:{}", loginQo.getLoginId(),loginQo.getPwd());
        if(!kacptchaUtil.checkKacptcha(loginQo.getCheckCodeKey(),loginQo.getCheckCode())){
            return Result.fail(ResultCode.CHECK_CODE_ERROR);
        }
        if(StrUtil.isEmpty(loginQo.getLoginId())||StrUtil.isEmpty(loginQo.getPwd())){
            return Result.fail(ResultCode.PARAM_VALID_ERROR);
        }

        if(UserLoginCode.LOGIN_BY_LOGIN_PHONE.getCode()==loginQo.getLoginWay()){
            return loginReal(UserLoginCode.LOGIN_BY_LOGIN_PHONE.getDatabaseField(),loginQo);
        }

        if(UserLoginCode.LOGIN_BY_LOGIN_ID.getCode()==loginQo.getLoginWay()){
            return loginReal(UserLoginCode.LOGIN_BY_LOGIN_ID.getDatabaseField(),loginQo);
        }

        return Result.fail(ResultCode.PARAM_VALID_ERROR);
    }



    private Result<UserVo> loginReal(String databaseFiled,UserLoginQo loginQo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(databaseFiled, loginQo.getLoginId());
        User user= this.getOne(queryWrapper);
        if(user==null){
            return Result.fail(ResultCode.NOT_FOUND);
        }

        if(user.getStatus()== UserCommonEnum.STATUS_UNREGISTER.getCode()){
            return Result.ObjectResult(UserCommonEnum.STATUS_UNREGISTER);
        }

        if(user.getStatus()== UserCommonEnum.STATUS_FROZEN.getCode()){
            return Result.ObjectResult(UserCommonEnum.STATUS_FROZEN);
        }

        boolean pwdCheck = BcryptUtil.check(loginQo.getPwd(), user.getPwd());
        if(!pwdCheck){
            return Result.fail(ResultCode.PARAM_VALID_ERROR);
        }

        StpUtil.login(user.getLoginId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setToken(StpUtil.getTokenValue());
        return Result.success(userVo);
    }

    @Override
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("登出成功");
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
        User user = this.getOne(queryWrapper);
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
            SysRole sysRole = sysRoleDao.selectOne(sysRoleQueryWrapper);
            // 如果角色信息为空，则跳过本次循环
            if(sysRole==null){
                continue;
            }
            // 将角色名称添加到角色列表中
            roles.add(sysRole.getRoleName());
        }
        return roles;
    }


}
