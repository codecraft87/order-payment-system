package org.orderpaymentsystem.exceptions;

public class PaymentCanNotBeRetry extends RuntimeException {

	public PaymentCanNotBeRetry(Long id) {
		super("Payment can not be retry "+id);
	}
	
	

}
