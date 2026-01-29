package org.orderpaymentsystem.exceptions;

public class OrderNotFoundForPaymentException extends RuntimeException {

	public OrderNotFoundForPaymentException(Long id) {
		super("Payment cannot be processed because the order does not exist:"+ id);
	}
}
