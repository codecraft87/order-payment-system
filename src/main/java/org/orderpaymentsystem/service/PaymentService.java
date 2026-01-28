package org.orderpaymentsystem.service;

import java.util.Date;

import org.orderpaymentsystem.common.enums.OrderStatus;
import org.orderpaymentsystem.common.enums.PaymentStatus;
import org.orderpaymentsystem.dto.PaymentDTO;
import org.orderpaymentsystem.entity.Order;
import org.orderpaymentsystem.entity.Payment;
import org.orderpaymentsystem.exceptions.InvalidateOrderForPaymentException;
import org.orderpaymentsystem.exceptions.OrderNotFoundForPaymentException;
import org.orderpaymentsystem.exceptions.PaymentCanNotBeRetry;
import org.orderpaymentsystem.exceptions.PaymentNotFoundException;
import org.orderpaymentsystem.repository.OrderRepository;
import org.orderpaymentsystem.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepo;
	
	private final OrderRepository orderRepo;
	
	public PaymentService(PaymentRepository repository, OrderRepository orderRepo) {
		this.paymentRepo = repository;
		this.orderRepo = orderRepo;
	}
	
	@Transactional
	public Long processPayment(PaymentDTO paymentDTO) {
		validatePaymentRequest(paymentDTO);
		Payment paymentToBeProcesed = new Payment(paymentDTO);
		paymentToBeProcesed.setStatus(PaymentStatus.PAYMENT_DONE);
		Date now = new Date();
		paymentToBeProcesed.setCreatedAt(now);
		paymentToBeProcesed.setUpdatedAt(now);
		return  paymentRepo.save(paymentToBeProcesed).getId();
	}

	public PaymentDTO getPaymentDetails(Long paymentId) {
		Payment payment = paymentRepo.findById(paymentId)
				.orElseThrow(()-> new PaymentNotFoundException(paymentId));
		return new PaymentDTO(payment);
	}
	
	@Transactional
	public Long retryPayment(PaymentDTO paymentDTO) {
		validateRePaymentRequest(paymentDTO);
		Payment paymentToBeRetry = paymentRepo.findById(paymentDTO.getPaymentId())
				.orElseThrow(()-> new PaymentNotFoundException(paymentDTO.getPaymentId()));
		paymentToBeRetry.setUpdatedAt(new Date());
		return paymentRepo.save(paymentToBeRetry).getId();		
	}

	private void validatePaymentRequest(PaymentDTO paymentDTO) {
		Order order = orderRepo.findById(paymentDTO.getOrderId())
				.orElseThrow(()-> new OrderNotFoundForPaymentException(paymentDTO.getOrderId()));
		if(order.getStatus()!=OrderStatus.CREATED) {
			throw new InvalidateOrderForPaymentException(order.getId());
		}
	}

	private void validateRePaymentRequest(PaymentDTO paymentDTO) {
		Order order = orderRepo.findById(paymentDTO.getOrderId())
				.orElseThrow(()-> new OrderNotFoundForPaymentException(paymentDTO.getOrderId()));
		if(order.getStatus()!=OrderStatus.PAYMENT_FAILED) {
			throw new PaymentCanNotBeRetry(paymentDTO.getPaymentId());
		}
	}
}
