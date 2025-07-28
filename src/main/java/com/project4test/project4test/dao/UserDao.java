package com.project4test.project4test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project4test.project4test.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
