package com.oracle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oracle.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value="SELECT p from Product p WHERE p.productCode IN :productCodes")
	List<Product> findAllProductsByProductCode(@Param("productCodes") List<String> productList);
	
}
