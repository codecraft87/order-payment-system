package org.orderpaymentsystem.exceptions;

public class OrderCannotBeModifiedException extends RuntimeException {
	public OrderCannotBeModifiedException(Long orderId) {
        super("Once payment initiated, Order " + orderId + " can not be modified");
    }
}
