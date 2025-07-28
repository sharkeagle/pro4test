package com.project4test.project4test.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String loginId;
    private String name;
    private String token;
}
