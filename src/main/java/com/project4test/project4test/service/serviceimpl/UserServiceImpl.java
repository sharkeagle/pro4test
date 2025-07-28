package com.project4test.project4test.service.serviceimpl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project4test.project4test.Qo.UserLoginQo;
import com.project4test.project4test.dao.UserDao;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.entity.User;
import com.project4test.project4test.enums.ResultCode;
import com.project4test.project4test.service.UserService;
import com.project4test.project4test.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
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
}
