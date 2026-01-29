package org.orderpaymentsystem.exceptions;

public class InvalidOrderStateForPaymentException extends RuntimeException {

	public InvalidOrderStateForPaymentException(Long id) {
		super("Payment is not allowed for this order:"+ id);
	}
}
