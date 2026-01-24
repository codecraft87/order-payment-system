package org.orderpaymentsystem.controller;

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

	@PostMapping
	public ResponseEntity<String> createOrder(){
		return new ResponseEntity<String>(null);
	}
	
	@GetMapping
	public ResponseEntity<String> retrieveOrderDetails(String orderId){
		return new ResponseEntity<String>(null);
	}
	
	@PutMapping
	@RequestMapping("{id}/cancel")
	public ResponseEntity<String> cancelOrder(@RequestParam String orderId){
		return new ResponseEntity<String>(null);
	}
}
