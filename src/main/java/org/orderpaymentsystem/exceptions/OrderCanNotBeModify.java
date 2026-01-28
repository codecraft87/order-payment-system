package org.orderpaymentsystem.exceptions;

public class OrderCanNotBeModify extends RuntimeException {
	public OrderCanNotBeModify(Long orderId) {
        super("Once payment initiated, Order " + orderId + " can not be modified");
    }
}
