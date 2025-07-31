package com.project4test.project4test.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductVo implements Serializable {
    /**
     * 版本验证：反序列化时，Java 虚拟机通过比较 serialVersionUID 来验证类版本是否一致。
     * 显式定义：显式定义 serialVersionUID 可避免因类结构变化导致自动生成的版本号改变，进而引发反序列化失败的问题。
     */
    private static final long serialVersionUID = 1L;
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 状态（1：上架，0：下架）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
