package com.project4test.project4test.qo;

import lombok.Data;

@Data
public class UserBaseQo {
    /**
     * 验证码
     */
    private String checkCode;
    /**
     * 验证码key
     */
    private String checkCodeKey;
    /**
     * 登录账号
     */
    private String loginPhone;
    /**
     * 登录密码
     */
    private String pwd;
}
