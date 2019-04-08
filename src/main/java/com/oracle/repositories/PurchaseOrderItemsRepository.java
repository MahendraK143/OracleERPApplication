package com.oracle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.model.PurchaseOrderItems;

public interface PurchaseOrderItemsRepository extends JpaRepository<PurchaseOrderItems, Long>{

	List<PurchaseOrderItems> findAllByPoId(Long poId);

}
