package org.orderpaymentsystem.exceptions;

public class PaymentCannotBeRetriedException extends RuntimeException {

	public PaymentCannotBeRetriedException(Long id) {
		super("Payment cannot be retried "+id);
	}
}
