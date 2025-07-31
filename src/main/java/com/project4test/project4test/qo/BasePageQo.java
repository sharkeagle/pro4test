package com.project4test.project4test.qo;

import lombok.Data;


@Data
public class BasePageQo {
    /**
     * 当前页码
     */
    private Integer pageNum=1;
    /**
     * 每页数量
     */
    private Integer pageSize=10;
    /**
     * 排序字段
     */
    private String sortField;
    /**
     * 排序顺序,true升序,false降序
     */
    private Boolean sortOrder;
}
