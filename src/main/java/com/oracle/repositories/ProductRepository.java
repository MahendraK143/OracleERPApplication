package com.oracle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
