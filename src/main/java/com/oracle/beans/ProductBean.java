package com.oracle.beans;

import com.oracle.model.ChildProduct;

import java.util.List;

public class ProductBean {
    private Long id;
    private String productCode;
    private String name;
    private Double cost;
    private String description;
    private String moq;
    private List<ChildProduct> childProducts;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getMoq() {
        return moq;
    }
    public void setMoq(String moq) {
        this.moq = moq;
    }

    public List<ChildProduct> getChildProducts() {
        return childProducts;
    }

    public void setChildProducts(List<ChildProduct> childProducts) {
        this.childProducts = childProducts;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", moq='" + moq + '\'' +
                ", childProducts=" + childProducts +
                '}';
    }
}
