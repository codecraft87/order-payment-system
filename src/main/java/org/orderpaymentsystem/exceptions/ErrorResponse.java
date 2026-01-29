package org.orderpaymentsystem.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String errorCode;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}