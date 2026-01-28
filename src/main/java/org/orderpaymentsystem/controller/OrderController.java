package org.orderpaymentsystem.controller;

import java.util.Optional;

import org.orderpaymentsystem.dto.Order;
import org.orderpaymentsystem.dto.OrderDTO;
import org.orderpaymentsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<String> createOrder(OrderDTO order){
		Optional<Long> orderId = orderService.createOrder(order);
		HttpStatus httpStatus = HttpStatus.CREATED;;
		String msg = null;
		
		if(orderId.isEmpty()) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = "Failed to create order";
		}else {
			msg = "Order created with "+ orderId.get().toString();
		}
		return ResponseEntity.status(httpStatus).body(msg);
	}
	
	@GetMapping
	public ResponseEntity<OrderDTO> retrieveOrderDetails(@RequestParam Long orderId){
		 Optional<OrderDTO> orderDtoOptional = orderService.getOrderDetails(orderId);
		 HttpStatus httpStatus = HttpStatus.OK;
		 OrderDTO orderDto = null;
		 String msg = null;
		 if(orderDtoOptional.isEmpty()) {
			 httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		 }else {
			 orderDto = orderDtoOptional.get();
		 }
		 return ResponseEntity.status(httpStatus).body(orderDto);
		 
	}
	
	@PutMapping
	@RequestMapping("{id}/cancel")
	public ResponseEntity<String> cancelOrder(@RequestParam String orderId){
		return new ResponseEntity<String>(null);
	}
}
