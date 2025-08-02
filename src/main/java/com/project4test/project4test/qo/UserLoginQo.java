package com.project4test.project4test.qo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginQo extends UserBaseQo{
    /**
     * 登录账号id
     */
    private String loginId;
    /**
     * 登录方式
     */
    private int loginWay;

}
