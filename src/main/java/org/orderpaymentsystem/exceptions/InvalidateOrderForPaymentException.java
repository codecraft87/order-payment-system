package org.orderpaymentsystem.exceptions;

public class InvalidateOrderForPaymentException extends RuntimeException {

	public InvalidateOrderForPaymentException(Long id) {
		super("Payment can not be processed for this order"+ id);
	}
}
