package com.anber.order.repository;

import com.anber.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
