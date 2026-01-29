package org.orderpaymentsystem.entity;

import java.util.Date;

import org.orderpaymentsystem.common.enums.PaymentStatus;
import org.orderpaymentsystem.dto.PaymentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	
	public Payment(PaymentDTO dto) {
		set(dto);
	}

	private void set(PaymentDTO dto) {
		this.orderId = dto.getOrderId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ORDER_ID")
	private Long orderId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PAYMENT_STATUS")
	private PaymentStatus status;
	
	@Column(name = "CREATED_AT")
	private Date createdAt;
	
	@Column(name = "UPDATED_AT")
	private Date updatedAt;
}
