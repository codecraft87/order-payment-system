package org.orderpaymentsystem.repository;

import org.orderpaymentsystem.common.enums.PaymentStatus;
import org.orderpaymentsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	boolean existsByOrderIdAndStatus(Long orderId, PaymentStatus status);

}
