package com.project4test.project4test.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project4test.project4test.dao.ProductDao;
import com.project4test.project4test.dto.Result;
import com.project4test.project4test.entity.Product;
import com.project4test.project4test.qo.ProductPageQo;
import com.project4test.project4test.service.ProductService;
import com.project4test.project4test.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Override
    public Result<IPage<ProductVo>> PageList(ProductPageQo productPageQo) {
        log.info("查询条件: {}", productPageQo);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        // contion字段作用,当参数为空时忽略
        queryWrapper.in(false,"name",productPageQo.getNames());

        queryWrapper.orderBy(false,productPageQo.getSortOrder(),productPageQo.getSortField());

        queryWrapper.eq(false,"status",productPageQo.getStatus());

        IPage<Product> page=this.page(new Page<>(productPageQo.getPageNum(),productPageQo.getPageSize()),queryWrapper);
        IPage<ProductVo> pageVo=new Page<>();
        BeanUtil.copyProperties(page,pageVo,"records");
        List<ProductVo> records= new ArrayList<>();

        for(Product product:page.getRecords()){
            ProductVo productVo=new ProductVo();
            BeanUtil.copyProperties(product,productVo);
            records.add(productVo);
        }

        pageVo.setRecords(records);

        return Result.success(pageVo);
    }
}
