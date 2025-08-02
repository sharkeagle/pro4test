package com.project4test.project4test.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRegisterQo extends UserBaseQo{
    /**
     * 用户名
     */
    private String name;
}
