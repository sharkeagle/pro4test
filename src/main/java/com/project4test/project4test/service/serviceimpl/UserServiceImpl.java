package com.project4test.project4test.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project4test.project4test.dao.UserDao;
import com.project4test.project4test.entity.User;
import com.project4test.project4test.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
