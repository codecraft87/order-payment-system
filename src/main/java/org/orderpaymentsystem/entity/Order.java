package org.orderpaymentsystem.entity;

import java.util.Date;

import org.orderpaymentsystem.common.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order")
public class Order {

	@Id
	private String id;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="ORDER_STATUS")
	private OrderStatus status;
	
	@Column(name="AMOUNT")
	private double amount;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
}
