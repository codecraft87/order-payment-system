package org.orderpaymentsystem.repository;

import org.orderpaymentsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}
