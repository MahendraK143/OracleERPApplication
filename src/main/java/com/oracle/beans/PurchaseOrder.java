package com.oracle.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.oracle.model.ChildProduct;

public class PurchaseOrder {
	private Long poId;
	private String purchaseOrderNumber;
	private String createdDate;
	private Double totalPrice;
	private List<ProductBean> productBeans;
	
	public List<ProductBean> getProductBeans() {
		return productBeans;
	}
	public void setProductBeans(List<ProductBean> productBeans) {
		this.productBeans = productBeans;
	}

	public Long getPoId() {
		return poId;
	}
	public void setPoId(Long poId) {
		this.poId = poId;
	}
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [poId=" + poId + ", purchaseOrderNumber=" + purchaseOrderNumber + ", createdDate="
				+ createdDate + ", totalPrice=" + totalPrice + ", productBeans=" + productBeans + "]";
	}


}
