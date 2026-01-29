package org.orderpaymentsystem.dto;

import java.util.Date;

import org.orderpaymentsystem.common.enums.PaymentStatus;
import org.orderpaymentsystem.entity.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {

	public PaymentDTO(Payment payment) {
		set(payment);
	}
	
	private void set(Payment payment) {
		this.paymentId = payment.getId();
		this.orderId = payment.getOrderId();
		this.createdAt = payment.getCreatedAt();
		this.updatedAt = payment.getUpdatedAt();
		this.status = payment.getStatus();
	}
	private Long paymentId;
	
	private Long orderId;
	
	private PaymentStatus status;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private boolean simulateFailure;
}
