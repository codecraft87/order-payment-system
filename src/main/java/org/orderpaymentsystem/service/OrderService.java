package org.orderpaymentsystem.service;

import java.util.Date;

import org.orderpaymentsystem.common.enums.OrderStatus;
import org.orderpaymentsystem.dto.OrderDTO;
import org.orderpaymentsystem.entity.Order;
import org.orderpaymentsystem.exceptions.OrderAlreadyCancelledException;
import org.orderpaymentsystem.exceptions.OrderCannotBeModifiedException;
import org.orderpaymentsystem.exceptions.OrderNotFoundException;
import org.orderpaymentsystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	private final OrderRepository repo;
	
	public OrderService(OrderRepository repo) {
		this.repo = repo;
	}
	
	@Transactional
	public Long createOrder(OrderDTO dto) {
		Order order = new Order(dto);
		Date now = new Date();
		order.setStatus(OrderStatus.CREATED);
		order.setCreatedAt(now);
		order.setUpdatedAt(now);
		return repo.save(order).getId();
	}
	
	public OrderDTO getOrderDetails(Long orderId) {
		Order orderDetails = repo.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException(orderId));
		return new OrderDTO(orderDetails);
	}
	
	@Transactional
	public OrderDTO cancelOrder(Long orderId){
		Order orderToCancel = repo.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException(orderId));
		if(orderToCancel.getStatus() == OrderStatus.ORDER_CANCELLED) {
			 throw new OrderAlreadyCancelledException(orderId);
		}
		orderToCancel.setStatus(OrderStatus.ORDER_CANCELLED);
		orderToCancel.setUpdatedAt(new Date());
		Order cancelledOrder = repo.save(orderToCancel);
		return new OrderDTO(cancelledOrder);
	}
	
	@Transactional
	public OrderDTO updateOrder(OrderDTO orderDto) {
		Order orderToupdate= repo.findById(orderDto.getOrderId())
				.orElseThrow(() -> new OrderNotFoundException(orderDto.getOrderId()));
		if(orderToupdate.getStatus() == OrderStatus.PAYMENT_DONE || 
			orderToupdate.getStatus() == OrderStatus.PAYMENT_FAILED || 
					orderToupdate.getStatus() == OrderStatus.PAYMENT_IN_PROGRESS){
			 	throw new OrderCannotBeModifiedException(orderDto.getOrderId());
			}
		orderToupdate.setUpdatedAt(new Date());
		orderToupdate.setAmount(orderDto.getAmount());
		orderToupdate.setUserId(orderDto.getUserId());
		Order updated = repo.save(orderToupdate);
		return new OrderDTO(updated);
	}
}

