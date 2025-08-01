package com.project4test.project4test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态码枚举
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    PARAM_VALID_ERROR(400, "参数校验失败"),
    NOT_LOGIN(405, "未登录"),
    USER_EXIST(406, "用户已存在"),
    PHONE_BOUND(407, "手机号已绑定"),
    CHECK_CODE_ERROR(408, "验证码错误");
    private final int code;
    private final String msg;
}