package org.orderpaymentsystem.service;

import java.util.Date;
import java.util.Optional;

import org.orderpaymentsystem.common.enums.OrderStatus;
import org.orderpaymentsystem.dto.OrderDTO;
import org.orderpaymentsystem.entity.Order;
import org.orderpaymentsystem.repository.OrderRepository;

public class OrderService {

	OrderRepository repo;
	
	public Optional<Long> createOrder(OrderDTO dto) {
		Order order = new Order(dto);
		order.setStatus(OrderStatus.CREATED);
		Date now = new Date();
		order.setCreatedAt(now);
		order.setUpdatedAt(now);
		Order createdOrder = repo.save(order);
		Long orderId = null;
		if(createdOrder!=null) {
			orderId = createdOrder.getId()
		}
		return Optional.of(orderId);
		
	}
	
	
	public Optional<OrderDTO> getOrderDetails(Long orderId) {
		Optional<Order> orderOptional = repo.findById(orderId);
		Order orderDetails = null;
		OrderDTO orderDTO = null;
		if(!orderOptional.isEmpty()) {
			orderDetails = orderOptional.get();
			orderDTO = new OrderDTO(orderDetails);
		}
		
		return Optional.of(orderDTO);
	}
	
	public String cancelOrder(Long orderId){
		Optional<Order> orderOptional = repo.findById(orderId);
		String errMsg = null;
		if(orderOptional.isEmpty()) {
			errMsg = "Unable to find order to cancel";
		}else {
			Order order = orderOptional.get();
			order.setStatus(OrderStatus.ORDER_CANCELLED);
			Order cancelledOrder = repo.save(order);
			if(cancelledOrder==null) {
				errMsg = "Failed to cancel order";
			}
		}
			
		return errMsg;
	}
	
	public String updateOrder(OrderDTO orderDto) {
		Optional<Order> orderOptional = repo.findById(orderDto.getOrderId());
		String errMsg = null;
		if(orderOptional.isEmpty()) {
			errMsg = "Unable to find order to update";
		}else {
			Order order = orderOptional.get();
			order.set(orderDto);
		}
		return errMsg;
	}
}

