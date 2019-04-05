package com.oracle.repositories;

import com.oracle.model.ChildProduct;
import com.oracle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildProductRepository extends JpaRepository<ChildProduct, Long> {
    List<ChildProduct> findByParentProductId(Long id);
}
