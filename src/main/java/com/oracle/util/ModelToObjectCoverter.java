package com.oracle.util;

import com.oracle.beans.ProductBean;
import com.oracle.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelToObjectCoverter {
    public static List<ProductBean> convertProductModelToBean(List<Product> productsModel) {
        List<ProductBean> productsBeans = new ArrayList();
        productsBeans = productsModel.stream().map(pm -> {
            ProductBean bean = new ProductBean();
            bean.setCost(pm.getCost());
            bean.setDescription(pm.getDescription());
            bean.setId(pm.getId());
            bean.setMoq(pm.getMoq());
            bean.setName(pm.getName());
            return bean;
        }).collect(Collectors.toList());
        return productsBeans;
    }
}
