package org.orderpaymentsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
public class PaymentController {

	@PostMapping
	public ResponseEntity<String> procesPayement(String orderId) {
		return new ResponseEntity<String>(null);
	}
}
