package com.project4test.project4test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户码枚举
 */
@Getter
@AllArgsConstructor
public enum UserLoginCode {
    LOGIN_BY_LOGIN_ID(1, "通过loginId登录","login_id"),
    LOGIN_BY_LOGIN_PHONE(2, "通过loginPhone登录","login_phone");
    private final int code;
    private final String msg;
    private final String databaseField;
}
