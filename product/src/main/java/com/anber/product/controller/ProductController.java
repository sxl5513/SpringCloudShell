package com.anber.product.controller;

import com.anber.product.dataobject.ProductCategory;
import com.anber.product.dataobject.ProductInfo;
import com.anber.product.enums.ResultEnum;
import com.anber.product.service.CategoryService;
import com.anber.product.service.ProductService;
import com.anber.product.utils.ResultVOUtil;
import com.anber.product.vo.ProductInfoVO;
import com.anber.product.vo.ProductVO;
import com.anber.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 1、查询所有在架的商品
     * 2、获取类目type列表
     * 3、查询类目
     * 4、构造数据
     * @return
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list(){
        //1、查询所有在架的商品
        List<ProductInfo> allUp = productService.findAllUp();
        //2、获取类目type列表
        List<Integer> categoryTypeList = allUp.stream().map(ProductInfo :: getCategoryType).collect(Collectors.toList());
        //3、查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //4、构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category: categoryList) {
            ProductVO vo = new ProductVO();
            vo.setCategoryName(category.getCategoryName());
            vo.setCategoryType(category.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: allUp) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO infoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, infoVO);
                    productInfoVOList.add(infoVO);
                }
            }
            vo.setProductInfoVOList(productInfoVOList);
            productVOList.add(vo);
        }
        return ResultVOUtil.success(productVOList);
    }
}
