package com.oracle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseOrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long poiId;
	private Long poId;
	private Long productId;
	private Long lineNo;
	private Integer quantity;
	
	public Long getLineNo() {
		return lineNo;
	}
	public void setLineNo(Long lineNo) {
		this.lineNo = lineNo;
	}

	public Long getPoiId() {
		return poiId;
	}
	public void setPoiId(Long poiId) {
		this.poiId = poiId;
	}
	public Long getPoId() {
		return poId;
	}
	public void setPoId(Long poId) {
		this.poId = poId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "PurchaseOrderItems [poiId=" + poiId + ", poId=" + poId + ", productId=" + productId + ", lineNo="
				+ lineNo + ", quantity=" + quantity + "]";
	}

}
