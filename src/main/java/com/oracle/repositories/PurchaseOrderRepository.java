package com.oracle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{

	List<PurchaseOrder> findAllPOByPurchaseOrderNumber(String poNumber);
	
}
