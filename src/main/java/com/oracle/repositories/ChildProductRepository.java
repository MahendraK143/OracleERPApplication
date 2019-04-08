package com.oracle.repositories;

import com.oracle.model.ChildProduct;
import com.oracle.model.Product;
import com.oracle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildProductRepository extends JpaRepository<ChildProduct, Long> {
    List<ChildProduct> findByParentProductId(Long id);

	@Query(value="SELECT cp from ChildProduct cp WHERE cp.productCode IN :productCodes")
	List<ChildProduct> findAllProductsByProductCode(@Param("productCodes") List<String> productList);

}
