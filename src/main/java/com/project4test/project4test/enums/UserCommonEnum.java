package com.project4test.project4test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserCommonEnum {
    STATUS_NORMAL(1, "正常"),
    STATUS_UNREGISTER(0, "已注销"),
    STATUS_FROZEN(2, "已冻结"),
    STATUS_DISABLED(3, "禁用");
    private final int code;
    private final String msg;

}
