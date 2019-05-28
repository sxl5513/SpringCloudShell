package com.anber.product.service.impl;

import com.anber.product.dataobject.ProductInfo;
import com.anber.product.dto.CartDto;
import com.anber.product.enums.ProductStatusEnum;
import com.anber.product.repository.ProductInfoRepository;
import com.anber.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findAllUp() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<CartDto> decreaseStockInputList) {

    }
}
