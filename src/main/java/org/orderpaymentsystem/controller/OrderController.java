package org.orderpaymentsystem.controller;

import org.orderpaymentsystem.dto.OrderDTO;
import org.orderpaymentsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;
	
	public OrderController(OrderService service) {
		this.orderService = service;
	}
	
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderDTO orderDto){
		Long orderId = orderService.createOrder(orderDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new OrderResponse(orderId, "Order Created"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> retrieveOrderDetails(@PathVariable("id") Long orderId){
			 return ResponseEntity.ok().body(orderService.getOrderDetails(orderId));
		 
	}
	
	@PutMapping("/{id}/cancel")
	public ResponseEntity<OrderResponse> cancelOrder(@PathVariable("id") Long orderId){
		OrderDTO cancelledOrder = orderService.cancelOrder(orderId);
		return ResponseEntity.ok().body(new OrderResponse(cancelledOrder.getOrderId(), "Order Cancelled"));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") Long orderId,
			@RequestBody OrderDTO orderDto){
		orderDto.setOrderId(orderId);
		OrderDTO updatedOrder = orderService.updateOrder(orderDto);
		return ResponseEntity.ok().body(updatedOrder);
	}
}
