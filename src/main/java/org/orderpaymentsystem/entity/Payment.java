package org.orderpaymentsystem.entity;

import java.util.Date;

import org.orderpaymentsystem.common.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {

	@Id
	private String id;
	
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="PAYMENT_STATUS")
	private PaymentStatus status;
	
	private Date createdAt;
	
	private Date updateAt;
}
