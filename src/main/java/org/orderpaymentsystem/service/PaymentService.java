package org.orderpaymentsystem.service;

import java.util.Date;

import org.orderpaymentsystem.common.enums.OrderStatus;
import org.orderpaymentsystem.common.enums.PaymentStatus;
import org.orderpaymentsystem.dto.PaymentDTO;
import org.orderpaymentsystem.entity.Order;
import org.orderpaymentsystem.entity.Payment;
import org.orderpaymentsystem.exceptions.DuplicatePaymentException;
import org.orderpaymentsystem.exceptions.InvalidOrderStateForPaymentException;
import org.orderpaymentsystem.exceptions.OrderNotFoundException;
import org.orderpaymentsystem.exceptions.OrderNotFoundForPaymentException;
import org.orderpaymentsystem.exceptions.PaymentCannotBeRetriedException;
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
		Long paymentId = null;
		if(paymentDTO.isSimulateFailure()) {
			paymentToBeProcesed.setStatus(PaymentStatus.PAYMENT_FAILED );
			Date now = new Date();
			paymentToBeProcesed.setCreatedAt(now);
			paymentToBeProcesed.setUpdatedAt(now);
			paymentId =  paymentRepo.save(paymentToBeProcesed).getId();
			updateOrderStatus(paymentDTO, OrderStatus.PAYMENT_FAILED);
		}else {
			if(paymentRepo.existsByOrderIdAndStatus(paymentDTO.getOrderId(), PaymentStatus.PAYMENT_DONE)) {
				throw new DuplicatePaymentException(paymentDTO.getOrderId());
			}
			paymentToBeProcesed.setStatus(PaymentStatus.PAYMENT_DONE);
			Date now = new Date();
			paymentToBeProcesed.setCreatedAt(now);
			paymentToBeProcesed.setUpdatedAt(now);
			paymentId =  paymentRepo.save(paymentToBeProcesed).getId();
			updateOrderStatus(paymentDTO, OrderStatus.PAYMENT_DONE);
		}
		return paymentId;
	}

	@Transactional
	public Long retryPayment(long paymentId) {
		Payment paymentToBeRetry = paymentRepo.findById(paymentId)
				.orElseThrow(()-> new PaymentNotFoundException(paymentId));
		PaymentDTO paymentDTO = new PaymentDTO(paymentToBeRetry);
		validateRePaymentRequest(paymentDTO);
		
		if(paymentRepo.existsByOrderIdAndStatus(paymentDTO.getOrderId(), PaymentStatus.PAYMENT_DONE)) {
			throw new DuplicatePaymentException(paymentDTO.getOrderId());
		}	
		paymentToBeRetry.setStatus(PaymentStatus.PAYMENT_DONE);
		paymentToBeRetry.setUpdatedAt(new Date());
		Long payId = paymentRepo.save(paymentToBeRetry).getId();
		updateOrderStatus(paymentDTO, OrderStatus.PAYMENT_DONE);

		return payId;
	}
	

	public PaymentDTO getPaymentDetails(Long paymentId) {
		Payment payment = paymentRepo.findById(paymentId)
				.orElseThrow(()-> new PaymentNotFoundException(paymentId));
		return new PaymentDTO(payment);
	}

	private void validatePaymentRequest(PaymentDTO paymentDTO) {
		Order order = orderRepo.findById(paymentDTO.getOrderId())
				.orElseThrow(()-> new OrderNotFoundForPaymentException(paymentDTO.getOrderId()));
		if(order.getStatus()!=OrderStatus.CREATED) {
			throw new InvalidOrderStateForPaymentException(order.getId());
		}
	}

	private void validateRePaymentRequest(PaymentDTO paymentDTO) {
		Order order = orderRepo.findById(paymentDTO.getOrderId())
				.orElseThrow(()-> new OrderNotFoundForPaymentException(paymentDTO.getOrderId()));
		if(order.getStatus()!=OrderStatus.PAYMENT_FAILED) {
			throw new PaymentCannotBeRetriedException(paymentDTO.getPaymentId());
		}
	}
	
	private void updateOrderStatus(PaymentDTO paymentDTO, OrderStatus status) {
		Order order = orderRepo.findById(paymentDTO.getOrderId())
				.orElseThrow(()->new OrderNotFoundException(paymentDTO.getOrderId()));
		order.setStatus(status);
		order.setUpdatedAt(new Date());
		orderRepo.save(order);
	}
}
