package org.orderpaymentsystem.controller;

import org.orderpaymentsystem.dto.PaymentDTO;
import org.orderpaymentsystem.service.PaymentService;
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
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService paymentService;
	
	public PaymentController(PaymentService service) {
		this.paymentService = service;
	}
	
	@PostMapping
	public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentDTO paymentDTO) {
		Long paymentId = paymentService.processPayment(paymentDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new PaymentResponse(paymentId,"Payment processed"));
	}
	
	@PutMapping("/{id}/retry")
	public ResponseEntity<PaymentResponse> retryPayment(@PathVariable("id") Long paymentId){
		Long payId =  paymentService.retryPayment(paymentId);
		return ResponseEntity.status(HttpStatus.OK).body(
				new PaymentResponse(payId,"Payment processed"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDTO> getPaymentDetails(@PathVariable("id") Long paymentId){
		PaymentDTO dto = paymentService.getPaymentDetails(paymentId);
		return ResponseEntity.ok().body(dto);
	}
}
