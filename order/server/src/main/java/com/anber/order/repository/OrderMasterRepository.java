package com.anber.order.repository;

import com.anber.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
