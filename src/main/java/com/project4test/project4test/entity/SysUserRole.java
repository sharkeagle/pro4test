package com.project4test.project4test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * <p>
 * 系统用户角色关联表
 * </p>
 *
 * @author zzh
 * @since 2025-07-29
 */
@TableName("sys_user_role")
@Data
public class SysUserRole {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;

}
