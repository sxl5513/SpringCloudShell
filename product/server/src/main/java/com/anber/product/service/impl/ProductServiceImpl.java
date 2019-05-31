package com.anber.product.service.impl;

import com.anber.product.dataobject.ProductInfo;
import com.anber.product.common.CartDto;
import com.anber.product.enums.ProductStatusEnum;
import com.anber.product.enums.ResultEnum;
import com.anber.product.exception.ProductException;
import com.anber.product.repository.ProductInfoRepository;
import com.anber.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    @Transactional
    public void decreaseStock(List<CartDto> decreaseStockInputList) {
        for(CartDto cart : decreaseStockInputList){
            //判断商品是否存在
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cart.getProductId());
            if (!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //判断库存是否足够
            ProductInfo productInfo = productInfoOptional.get();
            int result = productInfo.getProductStock() - cart.getProductQuantity();
            if(result < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
