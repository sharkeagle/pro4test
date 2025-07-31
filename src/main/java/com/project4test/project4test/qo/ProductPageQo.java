package com.project4test.project4test.qo;


import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPageQo extends BasePageQo {
    /**
     * 名称集合
     */
    public List<String> names;
    /**
     * 状态（1：上架，0：下架）
     */
    private int status=1;
}
