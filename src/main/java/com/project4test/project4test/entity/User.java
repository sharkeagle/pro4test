package com.project4test.project4test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.project4test.project4test.enums.UserCommonEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String pwd;
    private String loginId;
    private String loginPhone;
    private String email;
    private String usedPhone;
    private int status = UserCommonEnum.STATUS_NORMAL.getCode();
    @Version
    private LocalDateTime updateTime;
}
