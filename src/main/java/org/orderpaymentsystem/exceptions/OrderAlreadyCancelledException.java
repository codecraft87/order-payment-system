package org.orderpaymentsystem.exceptions;

public class OrderAlreadyCancelledException extends RuntimeException {
	public OrderAlreadyCancelledException(Long orderId) {
        super("Order "+orderId + " is already cancelled ");
    }
}
