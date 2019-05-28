package com.anber.product.service;

import com.anber.product.dataobject.ProductInfo;
import com.anber.product.dto.CartDto;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findAllUp();

    List<ProductInfo> findList(List<String> productIdList);

    void decreaseStock(List<CartDto> decreaseStockInputList);
}
