package com.project4test.project4test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_role")
public class SysRole {
    private Long id;
    private String roleName;
    private String roleCode;
    private String description;
    private int status;
    private Date createTime;
    private Date updateTime;
}
