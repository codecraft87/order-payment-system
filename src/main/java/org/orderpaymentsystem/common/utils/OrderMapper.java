package org.orderpaymentsystem.common.utils;

import org.orderpaymentsystem.dto.OrderDTO;
import org.orderpaymentsystem.entity.Order;

public class OrderMapper {

	public static Order toEntity(OrderDTO dto) {
		Order order = new Order(dto);
		return order;
	}
	
	public static OrderDTO toDto(Order order) {
		return new OrderDTO(order);
	}
}
