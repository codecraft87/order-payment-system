package org.orderpaymentsystem.exceptions;

public class PaymentNotFoundException extends RuntimeException {

	public PaymentNotFoundException(Long id) {
		super("Invalid payment id  "+id);
	}
}
