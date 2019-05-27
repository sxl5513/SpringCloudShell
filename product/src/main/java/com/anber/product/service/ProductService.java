package com.anber.product.service;

import com.anber.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findAllUp();
}
