package org.orderpaymentsystem.exceptions;

public class OrderNotFoundForPaymentException extends RuntimeException {

	public OrderNotFoundForPaymentException(Long id) {
		super("Payment can not be processed as this order does not exist:"+ id);
	}
}
