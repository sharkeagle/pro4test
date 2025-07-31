package com.project4test.project4test.qo;

import lombok.Data;

@Data
public class UserLoginQo {
    /**
     * 登录账号id
     */
    private String loginId;
    /**
     * 登录手机号
     */
    private String loginPhone;
    /**
     * 登录账号密码
     */
    private String pwd;
    /**
     * 登录方式
     */
    private int loginWay;
}
