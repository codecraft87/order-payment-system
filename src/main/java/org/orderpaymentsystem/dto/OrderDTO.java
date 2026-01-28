package org.orderpaymentsystem.dto;

import org.orderpaymentsystem.entity.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
	private String userId;
	private Double amount;
	private Long orderId;
	private String status;
	
	public OrderDTO(Order order) {
		set(order);
	}
	
	public void set(Order order) {
		this.userId = order.getUserId();
		this.amount = order.getAmount();
		this.orderId = order.getId();
		this.status = order.getStatus().toString();
	}
}
