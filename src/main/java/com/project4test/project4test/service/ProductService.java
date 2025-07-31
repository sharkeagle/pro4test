package com.project4test.project4test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.entity.Product;
import com.project4test.project4test.qo.ProductPageQo;
import com.project4test.project4test.vo.ProductVo;

public interface ProductService extends IService<Product> {
    Result<IPage<ProductVo>> PageList(ProductPageQo productPageQo);
}
