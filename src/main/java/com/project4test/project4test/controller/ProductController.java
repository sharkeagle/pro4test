package com.project4test.project4test.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.qo.ProductPageQo;
import com.project4test.project4test.service.ProductService;
import com.project4test.project4test.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductService productService;

    @SaCheckLogin
    @RequestMapping("/list")
    public Result<IPage<ProductVo>> list(ProductPageQo productPageQo) {
        return productService.PageList(productPageQo);
    }
}
