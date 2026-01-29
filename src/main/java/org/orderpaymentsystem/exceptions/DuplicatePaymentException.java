package org.orderpaymentsystem.exceptions;

public class DuplicatePaymentException extends RuntimeException {

	public DuplicatePaymentException(Long id) {
		super("Duplicate payment detected for order "+id);
	}
}
