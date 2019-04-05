package com.oracle.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String productCode;
	private String name;
	private Double cost;
	private String description;
	private String moq;

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

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productCode='" + productCode + '\'' +
				", name='" + name + '\'' +
				", cost=" + cost +
				", description='" + description + '\'' +
				", moq='" + moq + '\'' +
				'}';
	}
}
